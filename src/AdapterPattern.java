import java.io.File;
import java.nio.charset.StandardCharsets;

interface Target {
    int fprintf(File handle, String str);
}

class Linux {
    int uprintf(String str, File handle){
        System.out.println("For Linux " + str + " is texted to " + handle.toString());
        return 0;
    }
}

class BSD {
    int uprintf(String str, File handle) {
        System.out.println("For BSD " + str + " is texted to " + handle.toString());
        return 0;
    }
}

class NT {
    int printf(byte[] charArray, File handle) {
        System.out.println("For BSD " + charArray.toString() + " is texted to " + handle.toString());
        return 0;
    }
}

class Adapter implements Target {
    private Linux linuxAdaptee;
    private BSD bsdAdaptee;
    private NT ntAdaptee;
    private int test;

    public Adapter(Linux linuxAdaptee) {
        this.linuxAdaptee = linuxAdaptee;
        test=0;
    }

    public Adapter(BSD bsdAdaptee) {
        this.bsdAdaptee = bsdAdaptee;
        test=1;
    }

    public Adapter(NT ntAdaptee) {
        this.ntAdaptee = ntAdaptee;
        test=2;
    }

    public Linux getLinuxAdaptee() {
        return linuxAdaptee;
    }

    public void setLinuxAdaptee(Linux linuxAdaptee) {
        this.linuxAdaptee = linuxAdaptee;
    }

    @Override
    public int fprintf(File handle, String str) {
        if () {
            return linuxAdaptee.uprintf(str, handle);
        } else if () {
            return bsdAdaptee.uprintf(str, handle);
        } else {
            byte[] charArray = str.getBytes(StandardCharsets.UTF_8);
            return ntAdaptee.printf(charArray, handle);
        }

    }
}

class Utility {
    public void Test (int print) {
        System.out.println("Test " + print);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        Target linuxTarget = new Adapter(new Linux());
        Target bsdTarget = new Adapter(new BSD());
        Target ntTarget = new Adapter(new NT());

        File handle = null;
        int linux = linuxTarget.fprintf(handle, "a");
        int bsd = bsdTarget.fprintf(handle,"b");
        int nt = ntTarget.fprintf(handle, "c");


        Utility ut = new Utility();
        ut.Test(linux);
        ut.Test(bsd);
        ut.Test(nt);
    }
}
