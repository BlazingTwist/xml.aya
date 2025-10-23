# xml.aya

This is an Aya package that leverages [VTD-XML](https://vtd-xml.sourceforge.io/) to provide aya-instructions for advanced XML processing.

With xml.aya you can:
- modify xml documents without any undesirable side effects (such as changing the whitespace in unrelated elements)
- Operate on miscellaneous XML nodes (Comments, Processing Instructions, Whitespace)
- Validate XML documents against Schema Definitions (XSD)

---

## Install

TODO

## Usage

For basic usage, you can check out the test-cases:
- [Formatting XML (and XHTML)](./src/test/aya/xml.formatter.aya)  
  covers minification and pretty-printing.
- [Modifying XML](./src/test/aya/xml.modify.aya)    
  [Modifying XMLNS](./src/test/aya/xmlns.modify.aya)
  covers removing/replacing/inserting of elements/attributes/text
- [Extracting XML](./src/test/aya/xml.extract.aya)  
  [Extracting XMLNS](./src/test/aya/xmlns.extract.aya)
  covers reading xml into aya types using type definitions

## Instruction Overview

### High Level XML Instructions `:(xml.*)`

TODO

### High Level XMLNS Instructions `:(xmlns.*)`

TODO

### XSD Instructions `:(xsd.*)`

TODO

### Low Level VTD Instructions `:(vtd.*)`

TODO