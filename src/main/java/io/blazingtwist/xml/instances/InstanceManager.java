package io.blazingtwist.xml.instances;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.InvalidInstanceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InstanceManager {

	private static final ArrayList<InstanceRecord> instances = new ArrayList<>();

	public static int createInstance(InstanceType type, InstanceData data) {
		final int instanceId;
		synchronized (instances) {
			instanceId = instances.size();
			instances.add(new InstanceRecord(type, data));
		}
		return instanceId;
	}

	public static <T extends InstanceData> T popInstance(
			NamedOperator inst,
			BlockEvaluator blockEvaluator,
			InstanceType... expectedTypes
	) throws InvalidInstanceException {
		return getInstance(AyaHelper.popInt(inst, blockEvaluator), expectedTypes);
	}

	public static <T extends InstanceData> T getInstance(int instanceId, InstanceType... expectedTypes) throws InvalidInstanceException {
		if (instanceId < 0 || instanceId >= instances.size()) {
			throw new InvalidInstanceException("instance '" + instanceId + "' does not exist.");
		}

		InstanceRecord instance = instances.get(instanceId);
		if (Arrays.stream(expectedTypes).noneMatch(et -> et == instance.type)) {
			if (expectedTypes.length == 1) {
				throw new InvalidInstanceException("expected instance '" + instanceId + "' to be '" + expectedTypes[0] + "', but found '" + instance.type + "'");
			} else {
				String expectedTypesStr = Arrays.stream(expectedTypes).map(Enum::name).collect(Collectors.joining(", "));
				throw new InvalidInstanceException("expected instance '" + instanceId + "' to be one of [" + expectedTypesStr + "], but found '" + instance.type + "'");
			}
		}
		if (instance.data == null || instance.data.isClosed()) {
			throw new InvalidInstanceException("instance '" + instanceId + "' is closed.");
		}

		//noinspection unchecked
		return (T) instance.data;
	}

	private static class InstanceRecord {
		public final InstanceType type;
		public final InstanceData data;

		public InstanceRecord(InstanceType type, InstanceData data) {
			this.type = type;
			this.data = data;
		}
	}
}
