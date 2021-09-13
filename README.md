# Yocto layer for Space Nerds In Space

This project is meant to be a learning project for me to get to grips with the yocto build system.
I decided to create a layer for [Space Nerds In Space](https://github.com/smcameron/space-nerds-in-space).

The layer is very basic and was configured to work with Dunfell for a system based on the rk3126 chip. I haven't tested on anything else so can't gaurantee it works.

this layer includes a tar ball of the resources needed to run SNIS as it can take a long time to download them normally. If you want to get the latest resources make the following change:

The step: 

    do_configure () {  
        oe_runmake  update-assets  
    }  

May take a very long time and is rerun with every build.
To solve this I created a tar ball of the resources and just had the applied every time.

You can use the following patch to add in the tar ball and remove the download:

    diff --git a/recipes-snis/snis/snis_0.1.bb b/recipes-snis/snis/snis_0.1.bb
    index 522bc90..f3b2d5c 100644
    --- a/recipes-snis/snis/snis_0.1.bb
    +++ b/recipes-snis/snis/snis_0.1.bb
    @@ -8,6 +8,7 @@ LIC_FILES_CHKSUM = "file://COPYING;md5=6cf39a166c3ae0ea480931400e24117a \
                        file://ssgl/LICENSE;md5=624dfda16c12bdd0d12ef1301e833212"

    SRC_URI = "git://github.com/smcameron/space-nerds-in-space.git \
    +           file://git.tar.gz \
            file://0001-disabled-voice-chat.patch \
            file://0001-remove-root-check.patch \
            file://0001-remove-openscad-stuff-as-yocto-build-is-always-tring.patch \
    @@ -34,7 +35,8 @@ FILES_${PN} = "/home/root/bin \
                "

    do_configure () {
    -       oe_runmake  update-assets
    +       #oe_runmake  update-assets
    +    :
    }

The structure of the git.tag.gx file must look like so:  

    git  
    └── share  
        └── snis  
            └── all the files  
