package com.android.BC_ver02;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class btnCalOnclickListener implements OnClickListener{

	private Context context;
	private String id;
	private Class<?> activityName;
	
	public btnCalOnclickListener(Context context, String id, Class<?> activityName) {
		super();
		this.context = context;
		this.id = id;
		this.activityName = activityName;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this.context, this.activityName);									
		intent.putExtra("id", this.id);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.context.startActivity(intent);
	}

}
