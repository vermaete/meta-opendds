DOC_TAO3_VERSION = "7.1.3"
DOC_TAO3_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO3_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO3_VERSION}.tar.bz2"

DDS_SRC_BRANCH = "master"
SRC_URI = "\
    git://github.com/OpenDDS/OpenDDS.git;protocol=https;branch=${DDS_SRC_BRANCH};name=opendds \
    ${DOC_TAO3_URI};name=ace_tao;striplevel=1;subdir=git/ace_tao \
    file://0010-WIP-test-filter-tests-that-should-not-run-on-the-tar.patch \
    file://0200-yocto-do-not-enforce-this-for-yocto.patch \
    file://0202-cmake-do-not-install-VERSION.txt.patch \
"

SRC_URI:append:class-target = "\
    file://0201-ace_tao-fix-the-compilation-for-target.patch \
"
require opendds.inc

SRCREV = "81660e1d67b2230b029c0cbe2430ab4317129ea2"
SRC_URI[ace_tao.sha256sum] = "82064421d2ec57cc2ab6c4465e62dabfe4c4e32d8e3a99678915701297102d11"
