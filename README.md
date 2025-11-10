# xml.aya

This is an Aya package that leverages [VTD-XML](https://vtd-xml.sourceforge.io/) to provide aya-instructions for advanced XML processing.

With xml.aya you can:
- modify xml documents without any undesirable side effects (such as changing the whitespace in unrelated elements)
- Operate on miscellaneous XML nodes (Comments, Processing Instructions, Whitespace)
- Validate XML documents against Schema Definitions (XSD 1.0)

---

## Install

TODO

## Usage

```aya
require xml {xml xmlns xpath xsd}
```

This indicates that you want to import the symbols (`xml`, `xmlns`, `xpath` and `xsd`) from the `xml` name (which resolves to pkg/xml.aya).  
Naturally, you may omit symbols that you don't need, or import all symbols using `require xml {*}`.

For basic usage, you can check out the test-cases:
- [Formatting XML (and XHTML)](./src/test/aya/xml.formatter.aya)  
  covers minification and pretty-printing.
- [Modifying XML](./src/test/aya/xml.modify.aya)    
  [Modifying XMLNS](./src/test/aya/xmlns.modify.aya)
  covers removing/replacing/inserting of elements/attributes/text
- [Extracting XML](./src/test/aya/xml.extract.aya)  
  [Extracting XMLNS](./src/test/aya/xmlns.extract.aya)
  covers reading xml into aya types using type definitions
- [Validate XML using XSD 1.0](./src/test/aya/xsd.validate.aya)

## Instruction Overview

You can bypass the aya classes and use the libraries' Instructions directly.  
There are 2 instruction stores:
- [XSD Instructions `:(xsd.*)`](#xsd-instructions-xsd)
- [VTD Instructions `:(vtd.*)`](#low-level-vtd-instructions-vtd)

### XSD Instructions `:(xsd.*)`

TODO

### Low Level VTD Instructions `:(vtd.*)`

This provides most of the functions you may already be familiar with from VTD-XMl.  
Some functions were modified to be easier to use, and a few new functions were introduced by xml.aya.

TODO