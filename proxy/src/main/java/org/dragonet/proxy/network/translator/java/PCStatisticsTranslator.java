/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view the LICENCE file for details.
 *
 * @author Dragonet Foundation
 * @link https://github.com/DragonetMC/DragonProxy
 */
package org.dragonet.proxy.network.translator.java;

import com.github.steveice10.mc.protocol.data.game.statistic.GenericStatistic;
import com.github.steveice10.mc.protocol.data.game.statistic.Statistic;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerStatisticsPacket;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dragonet.proxy.network.session.ProxySession;
import org.dragonet.proxy.network.translator.PacketTranslator;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PCStatisticsTranslator implements PacketTranslator<ServerStatisticsPacket> {
    public static final PCStatisticsTranslator INSTANCE = new PCStatisticsTranslator();

    @Override
    @SuppressWarnings("unchecked")
    public void translate(ProxySession session, ServerStatisticsPacket packet) {
        if(session.getFutureMap().containsKey("stats")) {
            session.getFutureMap().get("stats").complete(packet.getStatistics());
            session.getFutureMap().remove("stats");
        }
    }
}
