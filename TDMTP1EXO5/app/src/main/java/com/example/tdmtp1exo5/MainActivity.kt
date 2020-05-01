package com.example.tdmtp1exo5
import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ListAdapter
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {
    lateinit var sendbtn : Button
    lateinit var loadbtn : Button
    lateinit var listContacts : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sendbtn=findViewById(R.id.SendBtn)

        loadbtn=findViewById(R.id.LoadBtn)
        listContacts=findViewById(R.id.listConstacts)
        var contacts: MutableList<Contact> = ArrayList()


        loadbtn.setOnClickListener(){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                    listContacts.text="permission denied"

                } else {
                    listContacts.text="permission allowed "

                    // No explanation needed, we can request the permission.

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                    var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)

                    var list = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                    )

                    if (list != null) {
                        while (list.moveToNext()) {
                            val name =
                                list.getString(list.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                            val phone =
                                list.getString(list.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            var contact = Contact(name, phone)
                            contacts.add(contact)
                        }
                        recyclerView.adapter = ListAdapter(contacts as ArrayList<Contact>)
                        list.close()
                    }
                }
        }
        }

        sendbtn.setOnClickListener() {
            var sms = SmsManager.getDefault()

            for (item in contacts){
                sms.sendTextMessage(item.phone, null, "SPAM MESSAGE !", null,null)
            }
        }
    }
    }

