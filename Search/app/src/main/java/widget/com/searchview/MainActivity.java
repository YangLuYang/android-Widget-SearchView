package widget.com.searchview;

import android.app.ActionBar;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar)findViewById(R.id.mainToolBar);
        toolBar.setTitle("SearchView");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        setSearchView(menu);
        /**
         * 关联检索配置和SearchView
         *
         * 实例化searchable，调用SearchView的setSearchAbleInfo（SearchAbleInfo）方法传递给SearchView
         * setSearchAbleInfo会根据XML文件产生一个 SearchableInfo对象，
         * 当在SearchView中进行搜索时，则会通过一个包含ACTION_SEARCH的intent来启动一个Activity
         */
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        SearchableInfo searchableInfo =
                searchManager.getSearchableInfo(
                        new ComponentName(getApplicationContext(), SearcherAty.class));
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }

    private void setSearchView(Menu menu) {
        MenuItem item = menu.getItem(0);
        searchView = new SearchView(this);
        searchView.setIconifiedByDefault(false);//设置展开后图标的样式,false时ICON在搜索框外,true为在搜索框内，无法修改
        searchView.setQueryHint("搜索");
        searchView.setSubmitButtonEnabled(true);//设置最右侧的提交按钮
//        searchView.setOnQueryTextListener(this);

        //设置搜索框文字颜色
        TextView textView = (TextView)searchView
                .findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setHintTextColor(Color.GRAY);
        textView.setTextColor(Color.WHITE);

       //设置关闭按钮
       ImageView closeButton = (ImageView)searchView
               .findViewById(android.support.v7.appcompat.R.id.search_close_btn);
       closeButton.setImageDrawable(getDrawable(R.drawable.ic_close_white));

       //设置跳转按钮
       ImageView goButton = (ImageView)searchView
               .findViewById(android.support.v7.appcompat.R.id.search_go_btn);
       goButton.setImageDrawable(getDrawable(R.drawable.ic_submit));

       //设置隐藏的ICON,在setIconifiedByDefault(false)时才能设置成功
       ImageView collapsedIcon = (ImageView)searchView
               .findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
       collapsedIcon.setImageDrawable(getDrawable(R.drawable.ic_search_white));


       item.setActionView(searchView);
    }

}
