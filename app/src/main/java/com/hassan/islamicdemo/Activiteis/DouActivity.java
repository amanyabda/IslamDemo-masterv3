package com.hassan.islamicdemo.Activiteis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.hassan.islamicdemo.R;


public class DouActivity extends AppCompatActivity {
    GridView gridView;

    String[] fruitNames = {"اليوم الاول","اليوم الثاني","اليوم الثالت","اليوم الرابع","اليوم الخامس","اليوم السادس",
            "اليوم السابع","اليوم الثامن","اليوم التاسع","اليوم العاشر","اليوم الحادي عشر","اليوم الثاني عشر","اليوم الثالث عشر",
            "اليوم الرابع عشر","اليوم الخامس عشر","اليوم السادس عشر","اليوم السابع عشر",
            "اليوم الثامن عشر","اليوم التاسع عشر","اليوم العشرون","اليوم الواحد والعشرون","اليوم الثاني والعشرون",
            "اليوم الثالث والعشرون","اليوم الرابع والعشرون",
            "اليوم الخامس والعشرون","اليوم السادس والعشرون","اليوم السابع والعشرون","اليوم الثامن والعشرون",
            "اليوم التاسع والعشرون","اليوم الثلاثون"};


    String[] fruitImages = {"اَلّهُمَّ اجْعَلْ صِيامي فيهِ صِيامَ الصّائِمينَ وَ قِيامي فيِهِ قِيامَ القائِمينَ، وَ نَبِّهْني فيهِ عَن نَوْمَةِالغافِلينَ، وَهَبْ لي جُرمي فيهِ يا اِلهَ العالمينَ، وَاعْفُ عَنّي يا عافِياًعَنِ المُجرِمينَ .",
            "اَلّهُمَّ قَرِّبْني فيهِ اِلى مَرضاتِكَ، وَجَنِّبْني فيهِ مِن سَخَطِكَ وَنَقِماتِكَ، وَ وَفِّقني فيهِ لِقِرائَةِ اياتِِكَ، بِرَحمَتِكَ يا أرحَمَ الرّاحمينَ . "
            ,"اَلّهُمَّ ارْزُقني فيهِ الذِّهنَ وَالتَّنْبيهِ، وَباعِدْني فيهِ مِنَ السَّفاهَةِ وَالتَّمْويهِ، وَ اجْعَل لي نَصيباً مِن كُلِّ خَيْرٍ تُنْزِلُ فيهِ، بِجودِكَ يا اَجوَدَ الأجْوَدينَ . ",
            "اَلّهُمَّ قَوِّني فيهِ عَلى اِقامَةِ اَمرِكَ، وَ اَذِقني فيهِ حَلاوَةِ ذِكْرِكَ، وَ اَوْزِعْني فيهِ لِأداءِ شُكْرِكَ بِكَرَمِكَ، وَاحْفَظْني فيهِ بِحِفظِكَ و َسَتْرِكَ يا اَبصَرَ النّاظِرينَ . "
            ,"اَلّهُمَّ اجعَلني فيهِ مِنَ المُستَغْفِرينَ، وَ اجعَلني فيهِ مِن عِبادِكَ الصّالحينَ القانِتينَ، وَ اجعَلني فيهِ مِن اَوْليائِكَ المُقَرَّبينَ، بِرَأفَتِكَ يا اَرحَمَ الرّاحمينَ . ",
            "اَلّهُمَّ لا تَخْذُلني فيهِ لِتَعَرُّضِ مَعصِيَتِكَ، وَلاتَضرِبني بِسِياطِ نَقِمَتِكَ، وَ زَحْزِحني فيهِ مِن موُجِبات سَخَطِكَ بِمَنِّكَ وَ اَياديكَ يا مُنتَهى رَغْبَةِ الرّاغِبينَ . ",
            "اَلّهُمَّ اَعِنّي فيهِ عَلى صِيامِهِ وَ قِيامِهِ، وَ جَنِّبني فيهِ مِن هَفَواتِهِ وَاثامِهِ، وَ ارْزُقني فيهِ ذِكْرَكَ بِدَوامِهِ،بِتَوْفيقِكَ يا هادِيَ المُضِّلينَ ",
            "اَلّهُمَّ ارْزُقْني فيهِ رَحمَةَ الأَيْتامِ وَ اِطعامَ الطَّعامِ وَاِفْشاءَ وَصُحْبَةَ الكِرامِ بِطَوْلِكَ يا مَلْجَاَ الأمِلينَ . ",
            "اَلّهُمَّ اجْعَل لي فيهِ نَصيباً مِن رَحمَتِكَ الواسِعَةِ، وَ اهْدِني فيهِ لِبَراهينِكَ السّاطِعَةِ، وَ خُذْ بِناصِيَتي إلى مَرْضاتِكَ الجامِعَةِ بِمَحَبَّتِكَ يا اَمَلَ المُشتاقينَ .",
            "اَلّهُمَّ اجْعَلني فيهِ مِنَ المُتَوَكِلينَ عَلَيْكَ، وَ اجْعَلني فيهِ مِنَ الفائِزينَ لَدَيْكَ، وَ اجعَلني فيه مِنَ المُقَرَّبينَ اِليكَ بِاِحْسانِكَ يا غايَةَ الطّالبينَ . ",
            "اَلّهُمَّ حَبِّبْ اِلَيَّ فيهِ الْإحسانَ، وَ كَرِّهْ فيهِ الْفُسُوقَ وَ العِصيانَ وَ حَرِّمْ عَلَيَّ فيهِ السَخَطَ وَ النّيرانَ بعَوْنِكَ ياغياثَ المُستَغيثينَ .",
            "َلّهُمَّ زَيِّنِّي فيهِ بالسِّترِ وَ الْعَفافِ، وَاسْتُرني فيهِ بِلِِِباسِ الْقُنُوعِ و َالكَفافِ، وَ احْمِلني فيهِ عَلَىالْعَدْلِ وَ الْإنصافِ، وَ آمنِّي فيهِ مِنْ كُلِّ ما اَخافُ بِعِصْمَتِكَ ياعصمَةَ الْخائفينَ . "
            ,"َلّهُمَّ طَهِّرْني فيهِ مِنَ الدَّنسِ وَ الْأقْذارِ، وَ صَبِّرْني فيهِ عَلى كائِناتِ الْأَقدارِ، وَ وَفِّقْني فيهِ لِلتُّقى وَ صُحْبَةِ الْأبرارِ بِعَوْنِكَ ياقُرَّةَ عَيْن الْمَساكينِ . " ,

            "اَلّهُمَّ لاتُؤاخِذْني فيهِ بالْعَثَراتِ، وَ اَقِلْني فيهِ مِنَ الْخَطايا وَ الْهَفَواتِ، وَ لا تَجْعَلْني فيهِ غَرَضاً لِلْبَلايا وَ الأفاتِ بِعزَّتِكَ ياعِزَّ المُسْلمينَ . ",
            "اَلّهُمَّ ارْزُقْني فيهِ طاعةَ الخاشعينَ، وَ اشْرَحْ فيهِ صَدري بِانابَةِ المُخْبِتينَ، بِأمانِكَ ياأمانَ الخائفينَ . ","َلّهُمَّ وَفِّقْني فيهِ لِمُوافَقَةِ الْأبرارِ، وَجَنِّبْني فيهِ مُرافَقَةِ الأشرارِ، وَآوني فيهِ برَحمَتِكَ إلى دارِ القَرارِبإلهيَّتِكَ يا إله العالمينَ .",
            "اَلّهُمَّ اهدِني فيهِ لِصالِحِ الأعْمالِ، وَ اقضِ لي فيهِ الحوائِجَ وَالآمالِ يا مَنْ لا يَحتاجُ إلى التَّفسيرِ وَ السُّؤالِ، يا عالِماً بِما في صُدُورِ العالمينَ صَلِّ عَلى مُحَمَّدٍ وَ آله الطّاهرينَ .",
            "اَلّهُمَّ نَبِّهني فيهِ لِبَرَكاتِ أسحارِهِ، وَنوِّرْ قَلْبي بِضِياءِ أنوارِهِ، وَ خُذْ بِكُلِّ أعْضائِي إلى اتِّباعِ آثارِهِ بِنُورِكَ يا مُنَوِّرَ قُلُوبِ العارفينَ . "
            ,"ألّهُمَّ وَفِّر فيهِ حَظّي مِن بَرَكاتِهِ، وَ سَهِّلْ سَبيلي إلىخيْراتِهِ، وَ لا تَحْرِمْني قَبُولَ حَسَناتِهِ يا هادِياً إلى الحَقِّ المُبينِ . "
            ,"ألّهُمَّ افْتَحْ لي فيهِ أبوابَ الجِنان، وَ أغلِقْ عَنَّي فيهِ أبوابَ النِّيرانِ، وَ وَفِّقْني فيهِ لِتِلاوَةِالقُرانِ يامُنْزِلَ السَّكينَةِ في قُلُوبِ المؤمنين . ",

            "ألّهُمَّ اجْعَلْ لي فيهِ إلى مَرضاتكَ دَليلاً، و لاتَجعَلْ لِلشَّيْطانِ فيهِ عَلَيَّ سَبيلاً، وَ اجْعَلِ الجَنَّةَ لي مَنْزِلاً وَمَقيلاً، يا قاضِيَ حَوائج الطالبينَ . "
            ,"ألّهُمَّ افْتَحْ لي فيهِ أبوابَ فَضْلِكَ، وَ أنزِل عَلَيَّ فيهِ بَرَكاتِكَ، وَ وَفِّقْني فيهِ لِمُوجِباتِ مَرضاتِكَ، وَ أسْكِنِّي فيهِ بُحْبُوحاتِ جَنّاتَكَ، يا مَجيبَ دَعوَةِ المُضْطَرِّينَ . ",

            "ألّهُمَّ اغْسِلني فيهِ مِنَ الذُّنُوبِ، وَطَهِّرْني فيهِ مِنَ العُيُوبِ، وَ امْتَحِنْ قَلبي فيهِ بِتَقْوى القُلُوبِ،يامُقيلَ عَثَراتِ المُذنبين . "
            ,"ألّهُمَّ إنِّي أسألُكَ فيهِ مايُرضيكَ، وَ أعُوذُ بِكَ مِمّا يُؤذيكَ،وَ أسألُكَ التَّوفيقَ فيهِ لِأَنْ اُطيعَكَ وَلا أعْصِيَكَ، يا جواد السّائلينَ ." ,

            "ألّهُمَّ اجْعَلني فيهِ مُحِبّاً لِأوْليائكَ، وَ مُعادِياً لِأعْدائِكَ، مُسْتَنّاً بِسُنَّةِ خاتمِ أنبيائكَ،يا عاصمَ قٌلٌوب النَّبيّينَ . ","ألّهُمَّ اجْعَلْ سَعْي فيهِ مَشكوراً، وَ ذَنبي فيهِ مَغفُوراً، وَعَمَلي فيهِ مَقبُولاً، وَ عَيْبي فيهِ مَستوراً يا أسمَعَ السّامعينَ . ",

            "ألّهُمَّ ارْزُقني فيهِ فَضْلَ لَيلَةِ القَدرِ، وَ صَيِّرْ اُمُوري فيهِ مِنَ العُسرِ إلى اليُسرِ، وَ اقبَلْ مَعاذيري وَ حُطَّ عَنِّي الذَّنب وَ الوِزْرَ، يا رَؤُفاً بِعِبادِهِ الصّالحينَ . "
            ,"ألّهُمَّ وَفِّرْ حَظِّي فيهِ مِنَ النَّوافِلِ، وَ أكْرِمني فيهِ بِإحضارِ المَسائِلِ، وَ قَرِّبْ فيهِ وَسيلَتي إليكَ مِنْ بَيْنِ الوَسائِلِ، يا مَن لا يَشْغَلُهُ إلحاحُ المُلِحِّينَ . ",
            "ألّهُمَّ غَشِّني فيهِ بالرَّحْمَةِ، وَ ارْزُقني فيهِ التَّوفيقَ وَ العِصْمَةَ، وَ طَهِّر قَلبي مِن غياهِبِ التُّهمَةِ، يارَحيماً بِعبادِهِ المُؤمنينَ . "
            ,"لّهُمَّ اجْعَلْ صِيامي فيهِ بالشُّكرِ وَ القَبولِ عَلى ماتَرضاهُ وَ يَرضاهُ الرَّسولُ مُحكَمَةً فُرُوعُهُ بِالأُصُولِ، بِحَقِّ سَيِّدِنامُحَمَّدٍ وَآلهِ الطّاهِرينَ، وَ الحَمدُ للهِ رَبِّ العالمين"};


    String [] Namesday = {"اليوم الاول","اليوم الثاني","اليوم الثالت","اليوم الرابع","اليوم الخامس","اليوم السادس",
            "اليوم السابع","اليوم الثامن","اليوم التاسع","اليوم العاشر","اليوم الحادي عشر","اليوم الثاني عشر","اليوم الثالث عشر",
            "اليوم الرابع عشر","اليوم الخامس عشر","اليوم السادس عشر","اليوم السابع عشر",
            "اليوم الثامن عشر","اليوم التاسع عشر","اليوم العشرون","اليوم الواحد والعشرون","اليوم الثاني والعشرون",
            "اليوم الثالث والعشرون","اليوم الرابع والعشرون",
            "اليوم الخامس والعشرون","اليوم السادس والعشرون","اليوم السابع والعشرون","اليوم الثامن والعشرون",
            "اليوم التاسع والعشرون","اليوم الثلاثون"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dou_activity);



        //        ******* اضافة
        MobileAds.initialize(this, "ca-app-pub-4574345653098269~4809814993");
        // View adContainer = findViewById(R.id.adView);
        AdView mAdView = new AdView(getApplicationContext());
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_sample));
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();

            }
        });
        AdRequest adRequest1 = new AdRequest.Builder()
                .build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest1);


        //finding listview
        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",Namesday[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);

            }
        });


    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data2,null);

            //getting view in row_data
            TextView name = view1.findViewById(R.id.rr);
            // ImageView image = view1.findViewById(R.id.images);

            name.setText(fruitNames[i]);
            // image.setImageResource(fruitImages[i]);
            return view1;



        }
    }



    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_sample));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
            }
        });
        return interstitialAd;
    }

    InterstitialAd mInterstitialAd;

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {

        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

}