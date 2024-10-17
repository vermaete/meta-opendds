SUMMARY = "OpenDDS Monitor"
DESCRIPTION = "A Qt application for monitoring OpenDDS domain participants and topics."
HOMEPAGE = "https://github.com/OpenDDS/opendds-monitor"
BUGTRACKER = "https://github.com/OpenDDS/opendds-monitor/issues"

LICENSE = "OpenDDS"
LIC_FILES_CHKSUM = "file://README.md;md5=f2c6593d44f9a9e5faec661f3bcd43d6"

DEPENDS += "\
    opendds-native \
    opendds \
    qwt-qt5 \
"

PV = "1.0+git"

SRC_URI = "gitsm://git@github.com/OpenDDS/opendds-monitor.git;protocol=ssh;branch=master"

SRCREV = "b1a2036047469b78eae9f9a731c8102cc15b2742"

S = "${WORKDIR}/git"

inherit cmake_qt5
