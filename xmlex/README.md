This directory contains some basic command-line executable examples for XML processing in Java. The examples are:

* PersonDOMExample.java - Intended to be run on person.xml, though it may work on others. It has an expression that may cause an exception to be thrown on some XML files (it outputs a particular node in the DOM based on the person.xml example, and depending on the XML file you give at the command-line, it may or may not have that node).
* DOMWalker.java - Walks the DOM outputting all nodes, even "empty" text nodes for whitespace
* SAXExample.java - uses SAX exvent parsing to output values and to output when certain "events" happen
* Transform.java - Applies an xsl transform to person.xml. 2 different XSL files are provided, one that transforms to HTML (person.xsl) and one that transforms to SQL (personsql.xsl)

A build.gradle is provided that will compile these to a standard build directory, or you can manually compile them via javac. You can then run each of these examples:
```
java -cp build/classes/main edu.asupoly.ser322.xmlex.SAXExample xmlfiles/personschema.xml 
```

The command-line arguments are the same (take a single XML filename) except for Transform.
The xmlfiles directory has various sample small XML files; some examples like PersonDOMExample and Transform expect person.xml, but others like DOMWalker and SAXExample are generic and could be run on any XML file if you want to try your own.
