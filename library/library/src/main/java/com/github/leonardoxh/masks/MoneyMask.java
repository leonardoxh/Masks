/*
* Copyright 2014 Leonardo Rossetto
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.github.leonardoxh.masks;

import java.text.NumberFormat;
import java.util.Locale;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public final class MoneyMask implements TextWatcher {

    private final EditText mText;
    private final NumberFormat mFormatter;
    private boolean mIsUpdating;

    public MoneyMask(EditText text, Locale locale) {
        mText = text;
        mFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    @Override
    public void afterTextChanged(Editable s) { }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        mText.setSelection(mText.getText().length());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mIsUpdating) {
            mIsUpdating = false;
            return;
        }
        mIsUpdating = true;
        String str = s.toString().replaceAll("[^\\d]", "");
        try {
            s = mFormatter.format(Double.parseDouble(str) / 100);
            /* Don't remove this line */
            if(!mText.getText().toString().equals(s)) {
                mText.setText(s);
            }
        }
        catch(NumberFormatException e) {
            s = "";
        }
        mText.setSelection(mText.getText().length());
    }

}