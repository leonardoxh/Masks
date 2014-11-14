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

import android.widget.EditText;

/**
 * Default input mask this will change the <code>#</code> char to an number
 * but this class also handle the two possible brazilian masks, for Sao Paulo
 * that have a 9 number first and for other countrys
 * @see com.github.leonardoxh.masks.Masks
 * @author Leonardo Rossetto
 */
public class BrazilPhoneMask extends InputMask {

    public BrazilPhoneMask(EditText editText) {
        super(editText, Masks.DEFAULT_BRAZIL_MASK);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().startsWith("(11)")) {
            mMask = Masks.DEFAULT_SAO_PAULO_MASK;
        } else {
            mMask = Masks.DEFAULT_BRAZIL_MASK;
        }
        super.onTextChanged(s, start, before, count);
    }

}
