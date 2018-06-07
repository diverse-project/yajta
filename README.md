# Yajta (Yet Another Java Tracing Agent)

Yajta is a extensible library for byte code probe insertion. It is built on top of javassist. It allows to build tracing agents but it is not limited to this task. Probe insertion can be done both offline and at load time. So far, probe can be inserted only at the begining and the end of methods and/or at any branching point.

Further more several tools that make us of that library are provided:

 * An agent that allow probe insertion at run time (See [Agent_README](Agent_README.md)) (Exemple of traces collected on common open source libraries can be found [here](https://github.com/KTH/execution-traces).)
 * An agent that check that an execution follow a previous trace (See [Follower_README](Follower_README.md))
 * A tool that generate a test impact report (A json file containing for each method of the explored project the list of test the call it.) (See [TIE_README](TIE_README.md))
 * A way to insert probe offline (See [API_README](API_README.md))


