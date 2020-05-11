package com.example.user.bulletfalls.Profile.Collection;

import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Storage.Sets.AbilitySet;

import java.util.Arrays;
import java.util.List;

public class UserCollection {

    private static UserCollection instance= new UserCollection();
    public   List<GameItemsCollection> userFulCollection;

    private UserCollection() {

    }

    public static UserCollection getInstance(){
        return instance;
    }

    public void succesfulTransaction(PossesAble possesAble){

        for(GameItemsCollection singleCollection : this.userFulCollection){
            if(possesAble.getClass().equals(singleCollection.getType())){
                try {
                    singleCollection.successfulTransaction(possesAble);
                } catch (YouAlreadyPossesThisCollectionItem youAlreadyPossesThisCollectionItem) {
                    youAlreadyPossesThisCollectionItem.printStackTrace();
                } catch (CollectionLacksException e) {
                    e.printStackTrace();
                } catch (NotAppropriatePossesAbleClassError notAppropriatePossesAbleClassError) {
                    notAppropriatePossesAbleClassError.printStackTrace();
                }
            }
        }
        saveCollection();
    }

    public boolean doYouOwnIt(PossesAble possesAble){

        for(GameItemsCollection singleCollection : this.userFulCollection){
            if(possesAble.getClass().equals(singleCollection.getType())||singleCollection.getType().isAssignableFrom(possesAble.getClass())){

                try {
                    boolean flag=singleCollection.doYouOwnIt(possesAble);
                    return flag;
                } catch (CollectionLacksException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    public void saveCollection(){

    }

    public void loadCollection(){

        this.userFulCollection=Arrays.asList(
                new GameItemsCollection(HeroSpecyfication.class),
                new GameItemsCollection(BulletSpecyfication.class) ,
                new GameItemsCollection(AbilitySpecyfication.class));

        this.userFulCollection.get(0).add(
                new CollectionItem(HE.CANDYCHIU.getValue(),true),
                new CollectionItem(HE.DIPPERPINES.getValue(),true),
                new CollectionItem(HE.GRENDA.getValue(),true),
                new CollectionItem(HE.LOGLANDGIRL.getValue(),true),
                new CollectionItem(HE.MABELPINES.getValue(),true),
                new CollectionItem(HE.MABLEWITHGRAPPLINGHOOK.getValue(),true),
                new CollectionItem(HE.OLDMANMCGUCKET.getValue(),true),
                new CollectionItem(HE.QUENTINTREBLEY.getValue(),true),
                new CollectionItem(HE.ROBIE.getValue(),true),
                new CollectionItem(HE.SOOSRAMIREZ.getValue(),true),
                new CollectionItem(HE.STANFORDPINNES.getValue(),true),
                new CollectionItem(HE.WADDLES.getValue(),false),
                new CollectionItem(HE.WANDYCOULDRON.getValue(),false)
                );
        this.userFulCollection.get(1).add(
                new CollectionItem(BE.COUNTERBULLET.getValue(),false),
                new CollectionItem(BE.DAM.getValue(),false),
                new CollectionItem(BE.DISARM.getValue(),false),
                new CollectionItem(BE.FIRSTJURNAL.getValue(),false),
                new CollectionItem(BE.GRENDAARMCHAIR.getValue(),true),
                new CollectionItem(BE.INCREASINGBULLET.getValue(),true),
                new CollectionItem(BE.LOG.getValue(),true),
                new CollectionItem(BE.RED.getValue(),true),
                new CollectionItem(BE.SECONDJURNAL.getValue(),true),
                new CollectionItem(BE.THIRDJURNAL.getValue(),true),
                new CollectionItem(BE.STANDARD.getValue(),true),
                new CollectionItem(BE.TIMEDAM.getValue(),true),
                new CollectionItem(BE.WENDYAXE.getValue(),true)
        );

        this.userFulCollection.get(2).add(
                new CollectionItem(AE.ABILITY.getValue(),true),
                new CollectionItem(AE.ARMCHAIRTHROW.getValue(),true),
                new CollectionItem(AE.DINOSUMMON.getValue(),true),
                new CollectionItem(AE.CARPEDIEM.getValue(),false),
                new CollectionItem(AE.FIRSTJURNAL.getValue(),true),
                new CollectionItem(AE.FIRSTSUMMON.getValue(),false),
                new CollectionItem(AE.FULLCOUNTER.getValue(),true),
                new CollectionItem(AE.INCREASESHOOTING.getValue(),true),
                new CollectionItem(AE.MULTIBEAVERSATTACK.getValue(),true),
                new CollectionItem(AE.NOTHING.getValue(),true),
                new CollectionItem(AE.PROGRESS.getValue(),true),
                new CollectionItem(AE.PSTEST.getValue(),true),
                new CollectionItem(AE.SECONDJURNAL.getValue(),true),
                new CollectionItem(AE.SUMMONLOG.getValue(),true),
                new CollectionItem(AE.THIRDJURNAL.getValue(),true),
                new CollectionItem(AE.THREEDINOSAURS.getValue(),true),
                new CollectionItem(AE.TIMEMACHINE.getValue(),true)
        );

    }
}
