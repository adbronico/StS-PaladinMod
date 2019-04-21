package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public class RemoveRandomDebuffAction extends AbstractGameAction
{
    private AbstractCreature player;

    public RemoveRandomDebuffAction(AbstractCreature player)
    {
        this.player = player;
    }

    public void update()
    {
        List<AbstractPower> debuffList = new ArrayList<>();
        for(AbstractPower power : player.powers)
        {
            if(AbstractPower.PowerType.DEBUFF == power.type)
            {
                debuffList.add(power);
            }
        }

        if(debuffList.size() > 0)
        {
            int debuffToRemove = AbstractDungeon.cardRandomRng.random(debuffList.size() - 1);
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, debuffList.get(debuffToRemove)));
        }

        this.isDone = true;
    }
}
