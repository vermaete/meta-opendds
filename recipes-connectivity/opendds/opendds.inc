SUMMARY = "OpenDDS is an open source C++ implementation of the Object Management Group (OMG) Data Distribution Service (DDS)"
HOMEPAGE = "https://opendds.org"
BUGTRACKER = "https://github.com/OpenDDS/OpenDDS/issues"

LICENSE = "OpenDDS"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11ee76f6fb51f69658b5bb8327c50b11"

inherit cmake ptest

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'tests', '', d)}"

PACKAGECONFIG[tests] = ", , googletest, perl packagegroup-meta-perl perl-module-tie-array perl-module-english"

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
EXTRA_OECMAKE:append:class-target = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", "-DOPENDDS_BUILD_TESTS=TRUE", "-DOPENDDS_BUILD_TESTS=FALSE", d)}"

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

do_install_ptest() {
    install -d ${D}${datadir}/DDS_ROOT/
    install -d ${D}${datadir}/DDS_ROOT/tests
    cd ${S}/tests
    tar --no-same-owner \
        --exclude='*.mpc'\
        --exclude='*.o' \
        --exclude='*.h' \
        --exclude='*.cpp' \
        --exclude='*.idl' \
        --exclude='*.inl' \
        --exclude='GNUmakefile*'\
        --exclude='.gitignore'\
        --exclude='.depend*'\
        --exclude='.obj*'\
        -cpf - . \
        | tar --no-same-owner -xpf - -C ${D}${datadir}/DDS_ROOT/tests

    install -d ${D}${datadir}/DDS_ROOT/performance-tests
    cd ${S}/performance-tests
    tar --no-same-owner \
        --exclude='*.mpc'\
        --exclude='*.o' \
        --exclude='*.h' \
        --exclude='*.cpp' \
        --exclude='*.idl' \
        --exclude='*.inl' \
        --exclude='GNUmakefile*'\
        --exclude='.gitignore'\
        --exclude='.depend*'\
        --exclude='.obj*'\
        -cpf - . \
        | tar --no-same-owner -xpf - -C ${D}${datadir}/DDS_ROOT/performance-tests

    install -d ${D}${datadir}/DDS_ROOT/DevGuideExamples
    cd ${S}/DevGuideExamples
    tar --no-same-owner \
        --exclude='*.mpc'\
        --exclude='*.o' \
        --exclude='*.h' \
        --exclude='*.cpp' \
        --exclude='*.idl' \
        --exclude='*.inl' \
        --exclude='GNUmakefile*'\
        --exclude='.gitignore'\
        --exclude='.depend*'\
        --exclude='.obj*'\
        -cpf - . \
        | tar --no-same-owner -xpf - -C ${D}${datadir}/DDS_ROOT/DevGuideExamples

    install -d ${D}${datadir}/DDS_ROOT/examples
    cd ${S}/examples
    tar --no-same-owner \
        --exclude='*.mpc'\
        --exclude='*.o' \
        --exclude='*.h' \
        --exclude='*.cpp' \
        --exclude='*.idl' \
        --exclude='*.inl' \
        --exclude='GNUmakefile*'\
        --exclude='.gitignore'\
        --exclude='.depend*'\
        --exclude='.obj*'\
        -cpf - . \
        | tar --no-same-owner -xpf - -C ${D}${datadir}/DDS_ROOT/examples

    # tests do expect this excutable here
    #install -d ${D}${bindir}
    #install -m 755 ${S}/tools/repoctl/repoctl ${D}${bindir}

    # A symbolic link because the test script will search the execuables under DDS_ROOT.
    # But because of the --prefix in the configure they are installed under /usr/bin
    ln -s ${bindir} ${D}${datadir}/DDS_ROOT/bin
    install -d ${D}/usr/lib/perl5/5.38.2/PerlACE
    install -m 644 ${S}/ace_tao/bin/PerlACE/*.pm ${D}/usr/lib/perl5/5.38.2/PerlACE
    install -d ${D}/usr/lib/perl5/5.38.2/PerlDDS
    install -m 644 ${S}/bin/PerlDDS/*.pm ${D}/usr/lib/perl5/5.38.2/PerlDDS
    install -d ${D}${datadir}/DDS_ROOT/tools/scripts/modules
    install -m 644 ${S}/tools/scripts/modules/*.pm ${D}${datadir}/DDS_ROOT/tools/scripts/modules
}

FILES:${PN}-ptest += "${libdir}/perl5/5.38.2/PerlACE ${libdir}/perl5/5.38.2/PerlDDS"

BBCLASSEXTEND = "native nativesdk"
