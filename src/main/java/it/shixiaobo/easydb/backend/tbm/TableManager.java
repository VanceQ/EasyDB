package it.shixiaobo.easydb.backend.tbm;

import it.shixiaobo.easydb.backend.utils.Parser;
import it.shixiaobo.easydb.backend.vm.VersionManager;
import it.shixiaobo.easydb.backend.dm.DataManager;
import it.shixiaobo.easydb.backend.parser.statement.Begin;
import it.shixiaobo.easydb.backend.parser.statement.Create;
import it.shixiaobo.easydb.backend.parser.statement.Delete;
import it.shixiaobo.easydb.backend.parser.statement.Insert;
import it.shixiaobo.easydb.backend.parser.statement.Select;
import it.shixiaobo.easydb.backend.parser.statement.Update;

public interface TableManager {
    BeginRes begin(Begin begin);
    byte[] commit(long xid) throws Exception;
    byte[] abort(long xid);

    byte[] show(long xid);
    byte[] create(long xid, Create create) throws Exception;

    byte[] insert(long xid, Insert insert) throws Exception;
    byte[] read(long xid, Select select) throws Exception;
    byte[] update(long xid, Update update) throws Exception;
    byte[] delete(long xid, Delete delete) throws Exception;

    public static TableManager create(String path, VersionManager vm, DataManager dm) {
        Booter booter = Booter.create(path);
        booter.update(Parser.long2Byte(0));
        return new TableManagerImpl(vm, dm, booter);
    }

    public static TableManager open(String path, VersionManager vm, DataManager dm) {
        Booter booter = Booter.open(path);
        return new TableManagerImpl(vm, dm, booter);
    }
}
