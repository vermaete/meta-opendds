SUMMARY = "OpenDDS example"
DESCRIPTION = "DevGuideExample DCPS Example from OpenDDS"
HOMEPAGE = "https://opendds.readthedocs.io/en/latest-release/devguide/getting_started.html"
LICENSE = "OpenDDS"
LIC_FILES_CHKSUM = "file://README.md;md5=3c38eddba8f9be09c2acc10026ca0ee1"

DEPENDS = "\
    opendds \
    opendds-native \
"

DDS_SRC_BRANCH = "branch-DDS-3.29"

SRC_URI = "\
    git://github.com/OpenDDS/OpenDDS.git;protocol=https;branch=${DDS_SRC_BRANCH};subpath=DevGuideExamples/DCPS/Messenger \
    file://readme.md \
"

SRCREV = "53414662d60f5e222aad9ca4383da0d856a577df"

S = "${WORKDIR}/Messenger"

inherit cmake

EXTRA_OECMAKE += "\
    -DCMAKE_PREFIX_PATH=${WORKDIR}/recipe-sysroot/usr/ \
"

do_install() {
    install -d ${D}${bindir}/${BPN}
    install -m 0755 publisher ${D}${bindir}/${BPN}
    install -m 0755 subscriber ${D}${bindir}/${BPN}
    install -m 0644 rtps.ini ${D}${bindir}/${BPN}
    install -m 0644 ${UNPACKDIR}/readme.md ${D}${bindir}/${BPN}
}

RDEPENDS:${PN} += "opendds"

INSANE_SKIP:${PN}-src += "buildpaths"
