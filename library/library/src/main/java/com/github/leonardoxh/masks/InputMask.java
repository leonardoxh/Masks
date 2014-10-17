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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Default input mask this will change the <code>#</code> char to an number
 * @see com.github.leonardoxh.masks.Masks
 * @author Leonardo Rossetto <leonardoxh@gmail.com>
 */
public class InputMask implements TextWatcher {

    private boolean isUpdating;
    private String mOldString = "";
    private final EditText mEditText;
    private final String mMask;

    public InputMask(EditText editText, String mask) {
        mEditText = editText;
        mMask = mask;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        String str = s.toString().replaceAll("[^\\d]", "");
        StringBuilder mask = new StringBuilder();
        if (isUpdating) {
            mOldString = str;
            isUpdating = false;
            return;
        }
        int i = 0;
        for(char m : mMask.toCharArray()) {
            if (m != '#' && str.length() > mOldString.length()) {
                mask.append(m);
                continue;
            }
            try {
                mask.append(str.charAt(i));
            } catch (Exception e) {
                break;
            }
            i++;
        }
        isUpdating = true;
        mEditText.setText(mask.toString());
        mEditText.setSelection(mask.length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void afterTextChanged(Editable s) { }

}
