package com.dailyreport.five;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailyreport.R;

public class FiveFragment extends Fragment {
    public ViewGroup rootview;
    private Button btapi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_shap_five, container, false);
        if (savedInstanceState != null) {

        }
        initSetting();
        return rootview;
    }

    private void initSetting() {
        btapi = rootview.findViewById(R.id.five_buttonapi);
        btapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("dongbusign://aerox"));
                intent.setData(Uri.parse("dongbusign://aerox?serviceID=3202208C8100006&custNo=19031826785&serverPublicKeyVer=2016072501&serverPublicKey=B4BFE7420646FA0D858D2E492444ED8C159BD179AEEC078AE7D34A8DFB0B988C89F672B5B930F82A2835925CCBC68C27124FC8222A9F97668DA22A17BFC31BCA23A88DD65417BCAA7892465460D6B0E6AF1A65195C09FAC2DFC3B240DBD98B77B138F81AFD23DF12160C689EFA4C82E13DAF0672A0728716CA50A3045F5CD1530BA2DCFD35245030568FAF7ACEAAC6E3CC2E83C0176DA929AFDFBF8E8D5A505742303068801179E22946F4C608AF0516275A5BDCC153C2EE6B25F126926C1D7C7DAEE09852F821713BFA8DEF0DAC844F8E8906337821F792A0F176B7C10E26C2AE64873E2FE6F79CCF84850ED0E6921F5A74DFD5164DDF837B44316259A9AA7B&ins_plhd_dvn=1&dvcd=2&ment=&userAge=38&userSex=2&browser=chrome&BIO_SIG_TOKEN_NO=83051042186316403475&sigTypeDvcd=inc_p_bio&srvDvcd=lti&encVal=303000044399252"));
                startActivity(intent);

            }
        });
    }
}
