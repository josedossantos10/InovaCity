package br.ufrpe.josed.inovacity.util;

import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

/**
 * Created by josed on 17/02/2018.
 */

public class Mascaras {


    public static MaskTextWatcher formatarCelular(TextView textView){

        SimpleMaskFormatter formatter = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher watcherCelular = new MaskTextWatcher(textView, formatter);
        return watcherCelular;
    }

    public static MaskTextWatcher formatarCPF(TextView textView){

        SimpleMaskFormatter formatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher watcher = new MaskTextWatcher(textView, formatter);
        return watcher;
    }

    public static MaskTextWatcher formatarCEP(TextView textView){

        SimpleMaskFormatter formatter = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher watcher = new MaskTextWatcher(textView, formatter);
        return watcher;
    }


}
