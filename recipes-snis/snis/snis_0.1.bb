inherit pkgconfig

LICENSE = "Unknown & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=6cf39a166c3ae0ea480931400e24117a \
                    file://share/snis/models/cargocontainer/LICENSE;md5=70d7fb121db5d92e6ce84c87ea3ade12 \
                    file://debian/copyright;md5=4b9c798520423a53bd5642d3561e7345 \
                    file://ssgl/COPYING;md5=5cb7514c8ba7a7deeb9ba1d1baa48784 \
                    file://ssgl/LICENSE;md5=624dfda16c12bdd0d12ef1301e833212"

SRC_URI = "git://github.com/smcameron/space-nerds-in-space.git \
           file://0001-disabled-voice-chat.patch \
           file://0001-remove-root-check.patch \
           file://0001-remove-openscad-stuff-as-yocto-build-is-always-tring.patch \
           file://0001-remove-root-check-from-snis-launcher.patch \
           file://0001-our-device-does-not-support-glsl-1.3-so-always-use-1.patch \
           file://0001-disable-Xwindows-hacks-and-build-without-opengl.patch \
           file://0001-add-default-host-ship-and-password-as-we-don-t-have-.patch \
           "

PV = "1.0+git${SRCPV}"
SRCREV = "808dd2424d0741498ca4050611de1fdd954c2738"

S = "${WORKDIR}/git"


DEPENDS = " glew libsdl2 portaudio-v19 libbsd libpng libvorbis lua openssl sox espeak"
RDEPENDS_${PN} = " xserver-xorg packagegroup-core-full-cmdline glew libsdl2 portaudio-v19 libbsd libpng libvorbis lua openssl sox espeak"

INSANE_SKIP_${PN} += "ldflags"

FILES_${PN} = "/home/root/bin \
               /home/root/share \
               /home/root/snis_launcher \
               "

do_configure () {
	oe_runmake  update-assets
}

do_compile () {
	oe_runmake all
}

do_install () {
	# oe_runmake install 'DESTDIR=${D}'
    install -d ${D}/home/root/bin
    install -d ${D}/home/root/share
    install -m 777 ${S}/bin/* ${D}/home/root/bin/
    install -m 777 ${S}/snis_launcher ${D}/home/root/
    cp -r --no-preserve=ownership ${S}/share/* ${D}/home/root/share/
}

