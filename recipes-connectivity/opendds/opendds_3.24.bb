# Specify SHA-1 for the release to avoid constantly checking the upstream repo.

SRCREV = "8f28fcea3cb786e01ac0f51f8a8e12854d3e9a5c"
DDS_SRC_BRANCH = "branch-DDS-3.24"
SRC_URI = "git://github.com/objectcomputing/OpenDDS.git;protocol=https;branch=${DDS_SRC_BRANCH};name=opendds"

require opendds.inc

DOC_TAO2_VERSION = "6.5.19"
DOC_TAO3_VERSION = "7.1.0"

DOC_TAO2_SHA256SUM = "e0d5faf9ec6fa457747776293eab2c71502109b6655de0e62f30dace04af809c"
DOC_TAO3_SHA256SUM = "6ad3d87dcafd8b60d35bc3b483d2aa010ceacdc89e426af5d98933839f22fbea"

DOC_TAO2_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO2_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO2_VERSION}.tar.gz"
DOC_TAO3_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO3_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO3_VERSION}.tar.gz"

ACE_TAO_OPTION ??= ""
ACE_TAO_URI ??= "${DOC_TAO2_URI}" 
ACE_TAO_SHA256SUM ??= "${DOC_TAO2_SHA256SUM}" 

python () {
    if d.getVar('ACE_TAO_OPTION') == '--doc-group':
        d.setVar('ACE_TAO_URI', '${DOC_TAO2_URI}')
        d.setVar('ACE_TAO_SHA256SUM', '${DOC_TAO2_SHA256SUM}')
    elif d.getVar('ACE_TAO_OPTION') == '--doc-group3':
        d.setVar('ACE_TAO_URI', '${DOC_TAO3_URI}')
        d.setVar('ACE_TAO_SHA256SUM', '${DOC_TAO3_SHA256SUM}')
}

SRC_URI += "${ACE_TAO_URI};name=ace_tao;unpack=0;subdir=git"
SRC_URI[ace_tao.sha256sum] = "${ACE_TAO_SHA256SUM}"

do_install:append:class-native() {
    # Prepare HOST_ROOT expected by DDS for target build
    mkdir -p ${D}${bindir}/DDS_HOST_ROOT/ACE_wrappers/bin
    mkdir -p ${D}${bindir}/DDS_HOST_ROOT/bin
    ln -sr ${D}${bindir}/opendds_idl ${D}${bindir}/DDS_HOST_ROOT/bin/opendds_idl
    ln -sr ${D}${bindir}/ace_gperf ${D}${bindir}/DDS_HOST_ROOT/ACE_wrappers/bin/ace_gperf
    ln -sr ${D}${bindir}/tao_idl ${D}${bindir}/DDS_HOST_ROOT/ACE_wrappers/bin/tao_idl
}
