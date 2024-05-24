# In order to use this recipe, add the following to either local.conf
# or an image recipe.
#
#  PREFERRED_VERSION:pn-opendds="1.0+git%"
#  PREFERRED_VERSION:pn-opendds-native="1.0+git%"
#
# It is not guaranteed to build. At any time the HEAD of master may not
# be compatible with the general build instructions in opendds.inc.

# Ensure that the versioned recipe is always preferred over this one.
DEFAULT_PREFERENCE = "-1"

PV = "1.0+git${SRCPV}"

DOC_TAO2_VERSION = "6.5.21"
DOC_TAO3_VERSION = "7.1.3"

DOC_TAO2_SHA256SUM = "212a9ab7be08aaca69d209d56e264143733d894d715dcb573eae909c5688e3e1"
DOC_TAO3_SHA256SUM = "7d394cfcc71d0e90824fd1399f93640b4c9423016b88974ad3e4694c5301d96a"

DOC_TAO2_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO2_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO2_VERSION}.tar.gz"
DOC_TAO3_URI = "https://github.com/DOCGroup/ACE_TAO/releases/download/ACE+TAO-${@'${DOC_TAO3_VERSION}'.replace('.','_')}/ACE+TAO-src-${DOC_TAO3_VERSION}.tar.gz"

DDS_SRC_BRANCH ??= "master"

# assume the git version (=master TIP) is ahead of the latest release
CVE_VERSION = "3.28.1"

# Checkout the head of the master branchs
SRCREV_opendds = "${AUTOREV}"

#SRCREV_acetao = "${AUTOREV}"
#SRCREV_mpc = "${AUTOREV}"

#SRCREV_FORMAT = "opendds_acetao_mpc"

#OECONF:append = "\
#    --ace-github-latest \
#    --ace=${S}/ACE_TAO/ACE \
#    --tao=${S}/ACE_TAO/TAO \
#    --mpc=${S}/ACE_TAO/MPC \
#"

require opendds.inc

#do_install:append:class-target() {
#    sed -i -e s:${S}/::g ${D}${libdir}/cmake/config.cmake
#}
#
