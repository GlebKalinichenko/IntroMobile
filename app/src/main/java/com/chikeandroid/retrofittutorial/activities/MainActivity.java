package com.chikeandroid.retrofittutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.chikeandroid.retrofittutorial.DividerItemDecoration;
import com.chikeandroid.retrofittutorial.R;
import com.chikeandroid.retrofittutorial.adapter.AnswersAdapter;
import com.chikeandroid.retrofittutorial.data.model.Item;
import com.chikeandroid.retrofittutorial.data.model.SOAnswersResponse;
import com.chikeandroid.retrofittutorial.data.remote.ApiUtils;
import com.chikeandroid.retrofittutorial.data.remote.SOService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @Override
    protected void onCreate (Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main );

        mService = ApiUtils.getSOService();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this.getApplicationContext(), new ArrayList<Item>(0), new AnswersAdapter.PostItemListener(){

            @Override
            public void onPostClick(Item item) {
                Intent intent = new Intent(MainActivity.this, ItemOwnerActivity.class);
                intent.putExtra(ItemOwnerActivity.OWNER_DATA, item);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("AnswersPresenter", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
               showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }


    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_menu_help) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivityForResult(intent, 0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
