package br.ufrpe.josed.inovacity.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

/**
 * Created by josed on 16/02/2018.
 */

public class Mensagens {



    public static void Alerta(Context context, String titulo, String msg){

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setNeutralButton("OK",null);
        dialog.show();

    }

    public static void Alerta(Context context, String titulo, String msg, String botao){

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setNeutralButton(botao,null);
        dialog.show();

    }

    public static void SnackCurto(View view, String msg){

        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("Action", null).show();

    }

    public static void SnackLongo(View view, String msg, String botao){

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(botao, null).show();

    }

    public static void ToastCurto(Context context, String msg){

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public static void ToastLongo(Context context, String msg){

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }





}
