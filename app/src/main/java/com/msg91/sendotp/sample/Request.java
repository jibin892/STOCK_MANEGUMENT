
package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.msg91.sendotp.sample.ui.gallery.NurseFragment;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Request extends AppCompatActivity {
    TextView pname, pname2, pname3,rid;
    ImageView pname4;
    Intent i;
    private Bitmap bitmap;
    private Uri filePath;
    Button bt, cart;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        pname = findViewById(R.id.pname);
        bt = findViewById(R.id.p_btn);
        cart = findViewById(R.id.cart);
        pname2 = findViewById(R.id.pname2);
        pname3 = findViewById(R.id.pname3);
        pname4 = findViewById(R.id.pname4);
        rid= findViewById(R.id.rid11);


        i = getIntent();
       // Toast.makeText(getApplicationContext(),i.getStringExtra("image" ), Toast.LENGTH_LONG).show();
        pname.setText(i.getStringExtra("name"));
        pname2.setText(i.getStringExtra("name1"));
        pname3.setText(i.getStringExtra("name2"));
        rid.setText(i.getStringExtra("id"));
        Picasso.get().load(i.getStringExtra("image")).into(pname4);



        i = getIntent();
        pname.setText(i.getStringExtra("name"));
        pname2.setText(i.getStringExtra("name1"));
        pname3.setText(i.getStringExtra("name2"));
        Picasso.get().load(i.getStringExtra("image")).into(pname4);
        rid.setText(i.getStringExtra("id"));
        sh = Objects.requireNonNull(getApplicationContext()).getSharedPreferences("data", MODE_PRIVATE);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), Purchaseconfirm.class);
                j.putExtra("amount", pname3.getText().toString());
                j.putExtra("pname", pname2.getText().toString());
                j.putExtra("pd", pname.getText().toString());
                j.putExtra("id", rid.getText().toString());
                j.putExtra("image",i.getStringExtra("image"));


                startActivity(j);
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Stock_manegument_system/cartitemcheck.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                              // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                if(response.contains("ok"))
                                {

                                    new SweetAlertDialog(Request.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText(" Item Already Added")
                                            .setContentText("Back to Home!")
                                            .setConfirmText("Yes")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    sDialog
                                                            .setTitleText("Logining...!")

                                                            .setConfirmText("OK")

                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    Intent in=new Intent(Request.this,MainActivity2.class);
                                                                    startActivity(in);
                                                                }
                                                            })
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                }
                                            })
                                            .show();




//
                                }
                                else{



                                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Stock_manegument_system/cart.php",
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                   // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                                    if(response.equals("Successful"))
                                                    {

                                                        new SweetAlertDialog(Request.this, SweetAlertDialog.WARNING_TYPE)
                                                                .setTitleText(" Item Adiing Success")
                                                                .setContentText("Back to Home!")
                                                                .setConfirmText("Yes")
                                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                    @Override
                                                                    public void onClick(SweetAlertDialog sDialog) {
                                                                        sDialog
                                                                                .setTitleText("Logining...!")

                                                                                .setConfirmText("OK")

                                                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                                    @Override
                                                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                                        Intent in=new Intent(Request.this,MainActivity2.class);
                                                                                        startActivity(in);
                                                                                    }
                                                                                })
                                                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                                    }
                                                                })
                                                                .show();




//
                                                    }

                                                }





                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }


                                            }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<>();
                                            //Adding parameters to request

                                            params.put("b",i.getStringExtra("name2"));
                                            params.put("c",i.getStringExtra("name"));
                                            params.put("a", i.getStringExtra("name1"));
                                            params.put("idd", i.getStringExtra("id"));
                                            params.put("d", sh.getString("phone",null));
                                            params.put("img",i.getStringExtra("image") );
                                            //returning parameter
                                            return params;
                                        }

                                    };


                                    //Adding the string request to the queue
                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(stringRequest);


                                }

                            }





                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }


                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("a", i.getStringExtra("id"));
                        params.put("b", sh.getString("phone",null));

                        return params;
                    }

                };


                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });
    }
}




































