package com.qiusm.eju.crawler.utils.ajk;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.LibraryResolver;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.DynarmicFactory;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * t和c方法是在ajk中拿出来的，对url进行加密前的一些处理
 *
 * @author qiushengming
 */
@Slf4j
public class AjkJniHookHandler extends AbstractJni {

    private final AndroidEmulator emulator;
    private final Module module;
    private final DvmClass cNative;
    private final VM vm;

    public AjkJniHookHandler() {
        this("ajk/libsignutil.so");
    }

    public AjkJniHookHandler(String osPath) {
        emulator = createArmEmulator();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(createLibraryResolver());

        vm = emulator.createDalvikVM();
        vm.setJni(this);
        vm.setVerbose(true);


        File file = new File(osPath + "/libsignutil.so");
        if (!file.exists()) {
            log.error("文件不存在，{}" + osPath);
        }
        DalvikModule dm = vm.loadLibrary(file, false);
        dm.callJNI_OnLoad(emulator);
        module = dm.getModule();

        cNative = vm.resolveClass("com/anjuke/mobile/sign/SignUtil");
    }

    /**
     * 创建库解析器
     *
     * @return LibraryResolver
     */
    private static LibraryResolver createLibraryResolver() {
        return new AndroidResolver(23);
    }

    /**
     * 创建ARM模拟器材
     *
     * @return AndroidEmulator
     */
    private static AndroidEmulator createArmEmulator() {
        return AndroidEmulatorBuilder.for32Bit()
                .setProcessName("com.sun.jna")
                .addBackendFactory(new DynarmicFactory(true))
                .build();
    }

    private void destroy() throws IOException {
        emulator.close();
        System.out.println("destroy");
    }

    /**
     * 调用ajk so中的getSign0方法
     */
    public String getSign0(String args1, String args2, HashMap hashMap, String args4, int args5) {
        String methodSign = "getSign0(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;";
        //        String methodSign = "setCacheFilePath(Ljava/lang/String;)V";
        HashMapObject object = new HashMapObject(vm, hashMap);
        // UnidbgPointer pointer = UnidbgPointer.pointer(emulator, hashMap.hashCode());
        StringObject ret = cNative.callStaticJniMethodObject(emulator, methodSign, args1, args2, object, args4, args5);
        return ret.getValue();
    }
}