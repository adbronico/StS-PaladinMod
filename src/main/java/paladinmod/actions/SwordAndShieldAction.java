package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SwordAndShieldAction extends AbstractGameAction
{
    private boolean freeToPlayOnce;
    private boolean upgraded;
    private AbstractPlayer player;
    private AbstractMonster monster;
    private DamageType damageType;
    private int energyOnUse;

    public SwordAndShieldAction(AbstractPlayer player, AbstractMonster monster, int amount, DamageType damageTypeForTurn, boolean upgraded, boolean freeToPlayOnce, int energyOnUse)
    {
        this.player = player;
        this.monster = monster;
        this.amount = amount;
        this.damageType = damageTypeForTurn;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
        this.actionType = ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update()
    {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
        {
            effect = this.energyOnUse;
        }

        if (this.player.hasRelic("Chemical X"))
        {
            effect += 2;
            this.player.getRelic("Chemical X").flash();
        }

        if(this.upgraded)
        {
            effect++;
        }

        if (effect > 0)
        {
            for (int i = 0; i < effect; ++i)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.monster, new DamageInfo(this.player, this.amount, this.damageType)));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.player, this.player, this.amount));
            }

            if (!this.freeToPlayOnce)
            {
                this.player.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
