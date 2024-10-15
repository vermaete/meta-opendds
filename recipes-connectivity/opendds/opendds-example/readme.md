OpenDDS demo application
========================

See:

* https://github.com/OpenDDS/OpenDDS/tree/master/DevGuideExamples/DCPS/Messenger
* https://opendds.readthedocs.io/en/latest-release/devguide/getting_started.html

Run publisher
-------------

```
cd /usr/bin/opendds-example
./publisher -DCPSConfigFile rtps.ini
```

Run subscriber
--------------

```
cd /usr/bin/opendds-example
./subscriber -DCPSConfigFile rtps.ini
```
