package com.webapp.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// ------------- WORK TOO -------------
// public class NativeEngine {
//     static {
//         System.loadLibrary("jr_bidir");
//     }
//
//     public static native String hello(String name);
// }

@Component 
public class NativeEngine {

    public NativeEngine(
        @Value("${native.library.path}") String libraryPath
    ) {
        System.load( libraryPath );
    }

    public static native String hello(String name);
}