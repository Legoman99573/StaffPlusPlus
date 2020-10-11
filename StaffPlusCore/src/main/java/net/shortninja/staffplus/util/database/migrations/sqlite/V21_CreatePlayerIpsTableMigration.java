package net.shortninja.staffplus.util.database.migrations.sqlite;

import net.shortninja.staffplus.util.database.migrations.Migration;

public class V21_CreatePlayerIpsTableMigration implements Migration {
    @Override
    public String getStatement() {
        return "CREATE TABLE IF NOT EXISTS sp_player_ips (  " +
            "player_uuid VARCHAR(36) NOT NULL,  " +
            "ip VARCHAR(15) NOT NULL,  " +
            "PRIMARY KEY (player_uuid, ip));";
    }

    @Override
    public int getVersion() {
        return 21;
    }
}
