DOC_TAO2_VERSION = "6.5.21"
DOC_TAO3_VERSION = "7.1.3"
DOC_TAO4_VERSION = "8.0.1"

DOC_TAO2_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO2_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO2_VERSION}.tar.gz"
DOC_TAO3_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO3_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO3_VERSION}.tar.gz"
DOC_TAO4_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO4_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO4_VERSION}.tar.gz"

DDS_SRC_BRANCH = "branch-DDS-3.30"
SRC_URI = "\
    git://github.com/OpenDDS/OpenDDS.git;protocol=https;branch=${DDS_SRC_BRANCH};name=opendds \
    ${@bb.utils.contains('PACKAGECONFIG', 'doc-group2', '${DOC_TAO2_URI};name=ace_tao2;unpack=1;subdir=git', '' ,d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'doc-group3', '${DOC_TAO3_URI};name=ace_tao3;unpack=1;subdir=git', '' ,d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'doc-group4', '${DOC_TAO4_URI};name=ace_tao4;unpack=1;subdir=git', '' ,d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'ishapes', 'file://0001-adding-the-ishapes-demo.patch', '', d)} \
    file://0010-WIP-test-filter-tests-that-should-not-run-on-the-tar.patch \
"

require opendds.inc

SRCREV = "e792fb8486972c9bd70097ffc42001808b1dc766"
SRC_URI[ace_tao2.sha256sum] = "212a9ab7be08aaca69d209d56e264143733d894d715dcb573eae909c5688e3e1"
SRC_URI[ace_tao3.sha256sum] = "7d394cfcc71d0e90824fd1399f93640b4c9423016b88974ad3e4694c5301d96a"
SRC_URI[ace_tao4.sha256sum] = "a5f7b647a6a7f617a4673e455c0c800b6793c478c560087bb693cc8cb94a8a61"
