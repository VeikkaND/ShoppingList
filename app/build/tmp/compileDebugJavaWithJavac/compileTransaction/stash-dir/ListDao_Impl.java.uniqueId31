package com.example.shoppinglist.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.shoppinglist.entities.List;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ListDao_Impl implements ListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<List> __insertionAdapterOfList;

  private final EntityDeletionOrUpdateAdapter<List> __deletionAdapterOfList;

  private final EntityDeletionOrUpdateAdapter<List> __updateAdapterOfList;

  public ListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfList = new EntityInsertionAdapter<List>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `List` (`listId`,`list_name`,`done_progress`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, List value) {
        stmt.bindLong(1, value.listId);
        if (value.listName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.listName);
        }
        stmt.bindLong(3, value.doneProgress);
      }
    };
    this.__deletionAdapterOfList = new EntityDeletionOrUpdateAdapter<List>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `List` WHERE `listId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, List value) {
        stmt.bindLong(1, value.listId);
      }
    };
    this.__updateAdapterOfList = new EntityDeletionOrUpdateAdapter<List>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `List` SET `listId` = ?,`list_name` = ?,`done_progress` = ? WHERE `listId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, List value) {
        stmt.bindLong(1, value.listId);
        if (value.listName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.listName);
        }
        stmt.bindLong(3, value.doneProgress);
        stmt.bindLong(4, value.listId);
      }
    };
  }

  @Override
  public void insertAll(final List... lists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfList.insert(lists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final List list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfList.handle(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateList(final List... lists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfList.handleMultiple(lists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List[] getAll() {
    final String _sql = "SELECT * FROM list";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "listId");
      final int _cursorIndexOfListName = CursorUtil.getColumnIndexOrThrow(_cursor, "list_name");
      final int _cursorIndexOfDoneProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "done_progress");
      final List[] _result = new List[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final List _item;
        _item = new List();
        _item.listId = _cursor.getInt(_cursorIndexOfListId);
        if (_cursor.isNull(_cursorIndexOfListName)) {
          _item.listName = null;
        } else {
          _item.listName = _cursor.getString(_cursorIndexOfListName);
        }
        _item.doneProgress = _cursor.getInt(_cursorIndexOfDoneProgress);
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
  public List findById(final int id) {
    final String _sql = "SELECT * FROM list WHERE listId LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "listId");
      final int _cursorIndexOfListName = CursorUtil.getColumnIndexOrThrow(_cursor, "list_name");
      final int _cursorIndexOfDoneProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "done_progress");
      final List _result;
      if(_cursor.moveToFirst()) {
        _result = new List();
        _result.listId = _cursor.getInt(_cursorIndexOfListId);
        if (_cursor.isNull(_cursorIndexOfListName)) {
          _result.listName = null;
        } else {
          _result.listName = _cursor.getString(_cursorIndexOfListName);
        }
        _result.doneProgress = _cursor.getInt(_cursorIndexOfDoneProgress);
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
  public int getListId(final String name) {
    final String _sql = "SELECT listId FROM list WHERE list_name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List findByName(final String name) {
    final String _sql = "SELECT * FROM list WHERE list_name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "listId");
      final int _cursorIndexOfListName = CursorUtil.getColumnIndexOrThrow(_cursor, "list_name");
      final int _cursorIndexOfDoneProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "done_progress");
      final List _result;
      if(_cursor.moveToFirst()) {
        _result = new List();
        _result.listId = _cursor.getInt(_cursorIndexOfListId);
        if (_cursor.isNull(_cursorIndexOfListName)) {
          _result.listName = null;
        } else {
          _result.listName = _cursor.getString(_cursorIndexOfListName);
        }
        _result.doneProgress = _cursor.getInt(_cursorIndexOfDoneProgress);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static java.util.List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
