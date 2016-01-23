OBJS = src/main.kt src/thoth.kt
CC = kotlinc
FLAGS = -include-runtime
OBJ_NAME = main.jar
LIB = lib/jsoup.jar

all:
	$(CC) $(OBJS) $(FLAGS) -cp $(LIB) -d $(OBJ_NAME)
run:
	java -cp .;main.jar;lib/jsoup.jar MainKt
