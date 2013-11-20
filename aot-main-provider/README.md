This is an example that demonstrates some things about how clojure AOT
compiling works.

The basic problem is that you *must* specify a :main namespace in your
leinengen project file if you want your uberjar to be runnable via
`java -jar`, and you also *must* do a `gen-class` in the :main namespace
in order to cause `lein uberjar` to aot-compile that namespace to
java bytecode (which is also a prereq for `java -jar`).

However, the gen-class has the side effect of running through the
entire list of namespaces that you :use or :require from your :main
namespace (transitively) and aot compiling all of those as well.  So
you end up in a situation where you've aot-compiled almost your entire
project, and then when you try to do interactive development on the
repl you get classloading precedence issues because you have
.class files on the classpath before your .clj files.  This makes it
necessary to run `lein clean` often, makes your jar potentially
incompatible with other versions of clojure, and is just generally
a pain in the ass.

To see this in action, run `lein uberjar` on this project, and then
check out the contents of the uberjar.  You'll notice that the
`real-main` namespace is compiled to a .class file.  This is what
we're trying to avoid.