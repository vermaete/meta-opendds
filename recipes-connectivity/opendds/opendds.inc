SUMMARY = "OpenDDS is an open source C++ implementation of the Object Management Group (OMG) Data Distribution Service (DDS)"
HOMEPAGE = "https://opendds.org"
BUGTRACKER = "https://github.com/OpenDDS/OpenDDS/issues"

LICENSE = "OpenDDS"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11ee76f6fb51f69658b5bb8327c50b11"

inherit cmake ptest

# This is needed to have the correct flags from Yocto and cmake in the
# by MPC generated Makefiles for the ace_tao subproject.
export CCFLAGS="${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}"

EXTRA_OECMAKE:append = "\
    -DOPENDDS_RAPIDJSON=FALSE \
"

EXTRA_OECMAKE:append:class-native = "\
    -DOPENDDS_JUST_BUILD_HOST_TOOLS=TRUE \
"

EXTRA_OECMAKE:append:class-target = "\
    -DOPENDDS_JUST_BUILD_HOST_TOOLS=FALSE \
    -DOPENDDS_BUILD_EXAMPLES=FALSE \
    -DOPENDDS_HOST_TOOLS=${STAGING_BINDIR_NATIVE}/.. \
"

DEPENDS += "\
    opendds-native \
    perl-native \
"

S = "${WORKDIR}/git"

# Set the build directory to be the source directory
B = "${S}"

# The cmake install step do not install tao_idl
do_install:append:class-native() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/ace_tao/TAO/TAO_IDL/tao_idl ${D}${bindir}/
}

BBCLASSEXTEND = "native nativesdk"
