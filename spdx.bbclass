#DEPENDS  =+ "nomossa-native"
python do_spdx () {
    import  subprocess
    staging = d.getVar('STAGING_BINDIR_NATIVE', True)
    source = d.getVar('S', True)
    packagename = d.getVar('PN', True)
    nomossa=staging+"/nomos/agent/nomossa"
    result = subprocess.Popen([nomossa, "-d", source], stdout=subprocess.PIPE).stdout.read()
    filename = "/tmp/result/"+packagename+".spdx"
    f = open(filename, 'w')
    print >> f, result
    f.close()
}

addtask spdx after do_patch before do_configure
