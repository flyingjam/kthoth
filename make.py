import subprocess
import os
import sys

compiler = "kotlinc"

flags = "-include-runtime"

obj_name = "main.jar"

def get_extension(path):
    _, ext = os.path.splitext(path)
    return ext

def get_source_files():
    src = map((lambda x : "src/" + x), os.listdir("src"))
    return list(filter((lambda x : (get_extension(x)) == ".kt"), src))

def get_libraries():
    libraries = map((lambda x : "lib/" + x), os.listdir("lib"))
    libstring = ""
    for lib in libraries:
        libstring += (lib + ";")

    return ["-cp", libstring]

def return_call():
    call = [compiler]
    call.extend(get_source_files())
    call.append(flags)
    call.extend(get_libraries())
    call.append("-d")
    call.append(obj_name)
    return call

print(return_call())
subprocess.call(return_call(), shell=True)

if __name__ == "__main__":
    run = False
    for arg in sys.argv:
        if arg == "run":
            run = True

    if run:
        pass
    else:
        subprocess.call(return_call(), shell=True)
#subprocess.call(["kotlinc", "src/main.kt", "src/thoth.kt", "-include-runtime", "-cp", "lib/jsoup.jar;lib/commons-codec.jar", "-d", "main.jar"], shell=True)
