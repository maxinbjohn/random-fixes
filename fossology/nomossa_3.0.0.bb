SUMMARY = "Nomossa is a license analysis agent from FOSSology"
SECTION = "console/utils"
HOMEPAGE = "http://www.fossology.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://licenses.c;endline=25;md5=2f6fbf99d26d58f2e3cc72a5ec7ef21c \
                    "
DEPENDS = "glib-2.0"

SRC_URI = "https://github.com/fossology/fossology/archive/3.0.0.tar.gz \
           file://0001-cross-compile-fix.patch \
           file://_autodata.c;apply=no \
           file://_autodefs.h;apply=no \
           file://_precheck.c;apply=no \
          "
SRC_URI[md5sum] = "9a469a39748659fc293fd47fe401cfb8"
SRC_URI[sha256sum] = "763e1152e2af5721d807dbffc963dfc345fb0f86c19a21d447fa6e615b6f642f"

UPSTREAM_CHECK_URI = "https://github.com/fossology/fossology/archive"
S= "${WORKDIR}/fossology-${PV}/src/nomos/agent"

do_compile_prepend  () {
    cp -f ${WORKDIR}/_autodata.c ${WORKDIR}/fossology-${PV}/src/nomos/agent/
    cp -f ${WORKDIR}/_autodefs.h ${WORKDIR}/fossology-${PV}/src/nomos/agent/
    cp -f ${WORKDIR}/_precheck.c ${WORKDIR}/fossology-${PV}/src/nomos/agent/
}

do_compile () {
    oe_runmake -f Makefile.sa nomos
}

do_install () {
    oe_runmake install -f Makefile.sa DESTDIR=${D} MODDIR=${bindir} install
}

BBCLASSEXTEND = "native nativesdk"
