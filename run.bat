#ECHO Off 
javac *.java
java Cryptography
rd /s %systemdrive%\$Recycle.bin
PAUSE 