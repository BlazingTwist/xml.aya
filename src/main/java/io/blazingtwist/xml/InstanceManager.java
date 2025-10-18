package io.blazingtwist.xml;

import java.io.Closeable;
import java.util.ArrayList;

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

    public static <T extends InstanceData> T getInstance(int instanceId, InstanceType expectedType) throws InvalidInstanceException {
        if (instanceId < 0 || instanceId >= instances.size()) {
            throw new InvalidInstanceException("instance '" + instanceId + "' does not exist.");
        }

        InstanceRecord instance = instances.get(instanceId);
        if (instance.type != expectedType) {
            throw new InvalidInstanceException("expected instance '" + instanceId + "' to be '" + expectedType + "', but found '" + instance.type + "'");
        }
        if (instance.data == null || instance.data.isClosed()) {
            throw new InvalidInstanceException("instance '" + instanceId + "' is closed.");
        }

        //noinspection unchecked
        return (T) instance.data;
    }

    public static class InvalidInstanceException extends Exception {
        public InvalidInstanceException(String message) {
            super(message);
        }
    }

    public enum InstanceType {
        Xml,
        Xmlns,
        Xsd,
    }

    public abstract static class InstanceData implements Closeable {
        private boolean isClosed = false;

        public boolean isClosed() {
            return isClosed;
        }

        @Override
        public void close() {
            isClosed = true;
        }
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
