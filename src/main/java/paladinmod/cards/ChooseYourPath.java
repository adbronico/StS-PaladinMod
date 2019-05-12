package paladinmod.cards;

import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;

import java.util.List;

public class ChooseYourPath extends AbstractPaladinCard implements ModalChoice.Callback
{
    public  static final String      ID                = "PaladinMod:ChooseYourPath";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/ChooseYourPath";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    private ModalChoice modal;

    public ChooseYourPath()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);

        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .addOption(new PathOfEquilibrium())
                .addOption(new PathOfPatience())
                .addOption(new PathOfPunishment())
                .create();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        return modal.generateTooltips();
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ChooseYourPath();
    }

    @Override
    public void optionSelected(AbstractPlayer player, AbstractMonster monster, int choice)
    {

    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            modal.getCard(0).upgrade();
            modal.getCard(1).upgrade();
            modal.getCard(2).upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        modal.open();
    }
}
