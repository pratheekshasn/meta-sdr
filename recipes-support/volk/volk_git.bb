SUMMARY = "The Vector Optimized Library of Kernels"
HOMEPAGE = "http://libvolk.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "boost python-cheetah-native"

inherit pythonnative cmake pkgconfig

PACKAGECONFIG ??= ""
PACKAGECONFIG[orc] = "-DENABLE_ORC=ON,-DENABLE_ORC=OFF,orc, "

export BUILD_SYS
export HOST_SYS="${MULTIMACH_TARGET_SYS}"
export STAGING_LIBDIR

PV = "0.0.1"

SRC_URI = "git://github.com/gnuradio/volk.git;branch=master \
          "
SRC_URI_append_ettus-e300 = "file://volk_config"

S = "${WORKDIR}/git"

SRCREV = "84ac7467a67c453471e6ab4348497745246908e9"

PACKAGES += "${PN}-modtool"

FILES_${PN} += "${ROOT_HOME}/.volk"
FILES_${PN}-modtool = "${bindir}/volk_modtool \
                       ${PYTHON_SITEPACKAGES_DIR}/volk_modtool"
FILES_${PN}-dev += "${libdir}/cmake"

do_install_append() {
	if [ -e ${WORKDIR}/volk_config ]; then
		install -d ${D}/${ROOT_HOME}/.volk
		install -m 644 ${WORKDIR}/volk_config ${D}/${ROOT_HOME}/.volk
	fi
}