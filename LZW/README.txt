		----------10/7/2013-----------

This folder contains the code for LZW coding and decoding algorithm that has been implemented in Java. To run the code follow the following steps:

For running the Encoder:

program file: "lzw.java"
1. The program uses "lzw_input.txt" as the input file. Put the text that has to be compressed here.

2. The program produces "lzw_compressed.txt" as output file which is the compressed version of the original file.

To run the code type the following commands in the windows command prompt in the given order:

javac lzw.java
java lzw

For running the Decoder:

program file: "lzw_decoder.java"
1. The program uses "lzw_compressed.txt" as the input file. This is the same as the compressed file generated in previous step.

2. The program produces "lzw_decoded.txt" as output file which is the decompressed version of the compressed file. The contents of this file should be the same as "lzw_input.txt".

To run the code type the following commands in the windows command prompt in the given order:

javac lzw_decoder.java
java lzw_decoder

Interim files (can be deleted safely) : "lzw_interim.txt", "lzw_interim1.txt"


-----end------

Aniruddh Ramrakhyani