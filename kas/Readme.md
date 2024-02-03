Building and installing the ishapes demo for RaspberryPi
========================================================


Installing Kas
--------------

see: https://kas.readthedocs.io/en/latest/userguide.html


Build the ishapes demo
----------------------

```
kas build kas/kas-opendds.yaml:kas/kas-local.yaml:kas/kas-rpi-ishapes.yaml
```


Write the sdcard
----------------

```
umount /dev/sdbX
umount /dev/sdbX
cd build/tmp/deploy/images/raspberrypi4-64/
sudo bmaptool copy core-image-minimal-xfce-raspberrypi4-64.rootfs.wic.bz2 /dev/sdX
```

Run the ishapes demo
--------------------

Open a terminal

```
ishapes
```