package com.neerajsingh.popularmovies.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neeraj.singh on 10/05/16.
 */
public class Review implements Parcelable{
//    id": "572d7bc1c3a3680fdb001d69",
//            "author": "Frank Ochieng",
//            "content": "Well another super-sized Marvel Comics superhero saga hits the big screen with the selected savior Captain America taking the top billing on the marquee. Thus, the pulsating popcorn pleaser **Captain America: Civil War** arrives on the scene to giddy audiences that have been loyal and fixated on the successful spring of mighty Marvel heroes that have been paraded to viewers throughout the last few years. Thankfully, **Captain America: Civil War** carries on the tradition of spry superhero-studded spectacles that have been glorious and adventurous from the stable of Marvel-based movies guaranteed to win over the enthusiastic hearts of avid comic book fanboys everywhere. Joyously overstuffed and convincingly extensive with a super team armed with crime-fighting excess, **Civil War** seizes the moment to introduce a noteworthy twist: two factions of rescuing superpowers engaging in some explosive in-house fighting led by two of Marvel Comics main standouts in Chris Evans’s Captain America and Robert Downey Jr.’s Iron Man.\r\n\r\nCo-directors Anthony Russo and Rene Russo were instinctively crafty to link the overly successful Iron Man film franchise to the **Captain America: Civil War** inner circle to ensure an even more treasured toxic atmosphere. Superhero fans will get a thrill of witnessing the extra add-ons concerning other dynamic titans to join the action-packed festivities involving Team Captain America versus Team Iron Man. True, **Civil War** has its share of flaws but that does not take away from this heroes-in-crisis flick demonstrating its ambitious overtones.\r\n\r\nSo what has caused the bad blood among the great and grand good guys known for protecting the world from evil dominance and destruction? Essentially the theme of collateral damage, the involvement regarding civilian-related deaths and injuries plus the world-wide backlash has created a controversy pitted against The Avengers. Specifically, Captain America (a.k.a. Steve Rogers) and Scarlet Witch/Wanda Maximoff (Elizabeth Olsen) are responsible for the boisterous battle that caused such negative sensation resulting in political turmoil. Avengers head honcho Iron Man (a.k.a. Tony Stark) is dealing with his own personal regrets in the ill-advised creation of the unpredictable Ultron. The political authority want to hold Iron Man, second-in-command Captain America and the rest of the Avengers accountable for the global devastation that have taking its toll when trying to oversee the potential harm wreaking havoc on humanity.\r\n\r\nLeading the charge in putting a watchful eye on the labeled reckless Avengers is Secretary of State Thaddeus Ross (William Hurt) that suggests the super group be monitored by the United Nations. Naturally friction develops between the superheroes that either agree with Ross’s UN restriction policies or disagree with being placed under a microscope that threatens to handcuff their free-wheeling heroic duties. All these contrasting beliefs eventually turn into epic back-and-forth confrontations where the raging Avengers are at odds with each other.\r\n\r\nFor Stark/Iron Man’s stance, he is willing to toe the line and ultimately agree that his crew needs to tone down their tenacious tactics as crime-stoppers. Iron Man’s consciousness, particularly in the case of a disillusioned mother (Alfre Woodward) making him feel guilt-ridden over her son’s death during an intense Battle of Sokovia, is probably the main factor behind his decision to have his team reigned in a bit from the political watchdogs. Siding with Iron Man’s viewpoint are the likes of Black Widow (Scarlett Johansson), Vision (Paul Bettany) and War Machine (Don Cheadle). As for Rogers/Captain America, he is not too thrilled being put in check by the intrusive governmental pencil-pushers that want to scrutinize the team’s every move. Standing with Captain America firmly are Falcon (Anthony Mackie) and the aforementioned Scarlet Witch.\r\n\r\nOn top of Captain America’s current Avengers-oriented strife in his contentious mingling with Iron Man and his ardent backers, he now finds himself trying to defend his old buddy-turned-wanted man Winter Soldier (Sebastian Stan) who is accused of killing civilians. Iron Man believes in Winter Soldier’s innocence and goes so far as to help him escape. Yes…Winter Soldier does come with more baggage attached to him–mainly in the form of the menacing Zemo (Daniel Bruhl).\r\n\r\nThe Russos and screenwriters Christopher Marckus and Stephen McFeely (all attached to the previous “Captain America: Winter Soldier”) provide the eyeful visual effects that predictably stimulate and effectively add to the overload of frenzied frolicking in this boisterous blockbuster. Certainly the deepened angst among this bombastic bunch works far more solidly than what was displayed in the stiffened and problematic Batman v. Superman. The notion that the entire globe and its leaders are weary of all the collective chaos at the hands of the Avengers trying to save their hides is a bit ridiculous. Besides, why are not the foes of the Avengers put on the hot coals for the societal ruination? It seems rather counterproductive to chastise the noble superpowers offering the safety of mankind yet the detractors not being grateful for the services that the Avengers bring to the table. It is somewhat convoluted to think that the global community are sour on our heralded heroes or that the heat generated within the walls will completely destroy the Avengers and their colorful, capable colleagues.\r\n\r\nThe movie’s aptly entitled **Civil War** does invite more punch to the proceedings especially when a who’s who of superhero showstoppers join the feisty fray at hand. The noted inclusion of defiant do-gooders are packed with the likes of Spider-Man (Tom Holland), the retired returnee Hawkeye (Jeremy Renner), Ant-Man (Paul Rudd), and nifty newcomer Black Panther (Chadwick Boseman). No doubt Captain America: Civil War is the impish and energizing launch pad for upcoming Marvel-induced movies waiting to make their future distinctive arrival on the big screen. Although Evans’s steady and charismatic Captain America more than holds his own as the solo act billed in the film’s title one cannot overlook Downey’s compelling Iron Man as the reliable source that lifts the profile of Evans’s Masked Wonder. This is indeed a collaborative big score for the glorified costumed cast but special kudos are reserved for Stan’s killing culprit as well as Boseman’s African president assuming the slick and resourceful Black Panther.\r\n\r\nYeah, **Captain America: Civil War** is true to its frenetic form as revved up entertainment preparing moviegoers for the upcoming summertime sizzle at the box office. After all, the on-screen Marvel Comics gravy train keeps moving merrily along so stay tuned.\r\n\r\n**Captain America: Civil War (2016)**\r\n\r\nWalt Disney Pictures\r\n\r\n2 hrs 26 mins.\r\n\r\nStarring: Chris Evans, Robert Downey, Jr., Scarlett Johansson, Sebastian Stan, Anthony Mackie, Don Cheadle, Jeremy Renner, Chadwick Boseman, Paul Bettany, Elizabeth Olsen, Paul Rudd, Daniel Bruhl, Alfre Woodward, William Hurt\r\n\r\nDirected by: Anthony Russo and Joe Russo\r\n\r\nMPAA Rating: PG-13\r\n\r\nGenre: Superheroes Saga/Action and Adventure/Science Fiction/Fantasy\r\n\r\nCritic’s Rating: *** stars (out of 4 stars)\r\n\r\n**(c) Frank Ochieng**",
//            "url": "https://www.themoviedb.org/review/572d7bc1c3a3680fdb001d69"
    @SerializedName("id")
    private String id;
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    protected Review(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }
}
