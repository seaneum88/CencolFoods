package assignments.comp304.com.cencolfoods;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OrderList extends Activity implements OnClickListener {

    Button coffee, sandwich, btnCheck;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        coffee = (Button)findViewById(R.id.coffee);
        sandwich = (Button)findViewById(R.id.sandwich);
        btnCheck = (Button)findViewById(R.id.btnCheck);

        coffee.setOnClickListener(this);
        sandwich.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        db=openOrCreateDatabase("customerDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS customer(cusID VARCHAR,cusName VARCHAR,cusOrder VARCHAR);");
    }

    @Override
    public void onClick(View view) {
        if(view==coffee)
        {
            db.execSQL("INSERT INTO customer VALUES('1','customer','Coffee');");
            showMessage("Success", "Coffee is added");
        }
        if(view==sandwich)
        {
            db.execSQL("INSERT INTO customer VALUES('2','customer','Sandwich');");
            showMessage("Success", "Sandwich is added");
        }


        switch (view.getId())
        {
            case R.id.btnCheck:
                startActivity(new Intent(this, Order.class));
        }

    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
