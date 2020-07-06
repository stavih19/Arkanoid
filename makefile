# 313541963
# staviha

compile: bin
	javac -cp biuoop-1.4.jar:. -d bin src/*.java

run:
	java -cp biuoop-1.4.jar:bin:resources Ass7Game

bin:
	mkdir bin

jar:
	jar -cfm ass7game.jar Manifest.txt -C bin . -C resources .