package com.example.shoppinglist.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.shoppinglist.entities.ListItem;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ListItemDao_Impl implements ListItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ListItem> __insertionAdapterOfListItem;

  private final EntityDeletionOrUpdateAdapter<ListItem> __deletionAdapterOfListItem;

  private final EntityDeletionOrUpdateAdapter<ListItem> __updateAdapterOfListItem;

  public ListItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListItem = new EntityInsertionAdapter<ListItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ListItem` (`listItemId`,`list_id`,`item_name`,`item_amount`,`item_unit`,`done`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListItem value) {
        stmt.bindLong(1, value.listItemId);
        stmt.bindLong(2, value.listId);
        if (value.itemName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.itemName);
        }
        stmt.bindDouble(4, value.itemAmount);
        if (value.itemUnit == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.itemUnit);
        }
        final int _tmp = value.done ? 1 : 0;
        stmt.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfListItem = new EntityDeletionOrUpdateAdapter<ListItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ListItem` WHERE `listItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListItem value) {
        stmt.bindLong(1, value.listItemId);
      }
    };
    this.__updateAdapterOfListItem = new EntityDeletionOrUpdateAdapter<ListItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ListItem` SET `listItemId` = ?,`list_id` = ?,`item_name` = ?,`item_amount` = ?,`item_unit` = ?,`done` = ? WHERE `listItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListItem value) {
        stmt.bindLong(1, value.listItemId);
        stmt.bindLong(2, value.listId);
        if (value.itemName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.itemName);
        }
        stmt.bindDouble(4, value.itemAmount);
        if (value.itemUnit == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.itemUnit);
        }
        final int _tmp = value.done ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.listItemId);
      }
    };
  }

  @Override
  public void insertAll(final ListItem... listItems) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfListItem.insert(listItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ListItem listItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfListItem.handle(listItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateListItem(final ListItem... listItems) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfListItem.handleMultiple(listItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public ListItem[] findByListId(final int listId) {
    final String _sql = "SELECT * FROM listitem WHERE list_id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, listId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfListItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "listItemId");
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "list_id");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "item_name");
      final int _cursorIndexOfItemAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "item_amount");
      final int _cursorIndexOfItemUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "item_unit");
      final int _cursorIndexOfDone = CursorUtil.getColumnIndexOrThrow(_cursor, "done");
      final ListItem[] _result = new ListItem[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final ListItem _item;
        _item = new ListItem();
        _item.listItemId = _cursor.getInt(_cursorIndexOfListItemId);
        _item.listId = _cursor.getInt(_cursorIndexOfListId);
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _item.itemName = null;
        } else {
          _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        }
        _item.itemAmount = _cursor.getFloat(_cursorIndexOfItemAmount);
        if (_cursor.isNull(_cursorIndexOfItemUnit)) {
          _item.itemUnit = null;
        } else {
          _item.itemUnit = _cursor.getString(_cursorIndexOfItemUnit);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfDone);
        _item.done = _tmp != 0;
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ListItem findById(final int id) {
    final String _sql = "SELECT * FROM listitem WHERE listItemId LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfListItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "listItemId");
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "list_id");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "item_name");
      final int _cursorIndexOfItemAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "item_amount");
      final int _cursorIndexOfItemUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "item_unit");
      final int _cursorIndexOfDone = CursorUtil.getColumnIndexOrThrow(_cursor, "done");
      final ListItem _result;
      if(_cursor.moveToFirst()) {
        _result = new ListItem();
        _result.listItemId = _cursor.getInt(_cursorIndexOfListItemId);
        _result.listId = _cursor.getInt(_cursorIndexOfListId);
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _result.itemName = null;
        } else {
          _result.itemName = _cursor.getString(_cursorIndexOfItemName);
        }
        _result.itemAmount = _cursor.getFloat(_cursorIndexOfItemAmount);
        if (_cursor.isNull(_cursorIndexOfItemUnit)) {
          _result.itemUnit = null;
        } else {
          _result.itemUnit = _cursor.getString(_cursorIndexOfItemUnit);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfDone);
        _result.done = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean[] getDones(final int listId) {
    final String _sql = "SELECT done FROM listitem WHERE list_id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, listId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean[] _result = new boolean[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final boolean _item;
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _item = _tmp != 0;
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
