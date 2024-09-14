MAIN_CLASS=com.inf5153.App
MVN= mvn
MVN_COMPILE=$(MVN) compile
MVN_INSTALL=$(MVN) clean install
MVN_EXEC_MAIN=$(MVN) exec:java --quiet -Dexec.mainClass=$(MAIN_CLASS) 
MVN_TEST=$(MVN) test

.PHONY: all compile run clean install

# Default target
all: compile

# Compile the project
compile:
	$(MVN_COMPILE)

# Install project artifacts into the local repository
install:
	$(MVN_INSTALL)

# Run the project
run: 
	@$(MVN_EXEC_MAIN)


# Run project tests
test:
	$(MVN_TEST)

# Clean the project
clean:
	$(MVN) clean
	@rm -rf src/main/java/com/inf5153/miniLang




