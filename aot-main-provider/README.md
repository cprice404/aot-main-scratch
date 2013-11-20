This is an example that demonstrates some things about how clojure AOT
compiling works.

-------------------------------------------------------------------
Initial commit
-------------------------------------------------------------------
(NOTE that the instructions below only apply to the initial commit;
 the second commit updates the project to get rid of the unnecessary
 aot; see additional notes below.)

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

--------------------------------------------------------------------
UPDATE: after second commit
--------------------------------------------------------------------

After changing the aot-main namespace to not `require` the `real-main`
namespace, and instead resolve it dynamically, we end up with the
same exact application but without having aot-compiled the `real-main`
namespace.  If you run `lein uberjar` now, and examine the contents,
you'll notice that the `real_main.clj` file is in the jar but there
is no pre-compiled version of it.