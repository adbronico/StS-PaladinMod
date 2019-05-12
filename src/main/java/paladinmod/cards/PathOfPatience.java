package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.PathOfPatiencePower;

public class PathOfPatience extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:PathOfPatience";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final int         DEX_GAIN_AMT      = 1;
    private static final int         DEX_GAIN_ADD      = 1;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.SPECIAL;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public PathOfPatience()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.cardDraw = this.baseCardDraw = CARD_DRAW_AMT;
        this.magicNumber = this.baseMagicNumber = DEX_GAIN_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new PathOfPatience();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(DEX_GAIN_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new PathOfPatiencePower(player, this.cardDraw, this.magicNumber)));
    }
}
