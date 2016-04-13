/*******************************************************************************
 * Copyright (c) 2015
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package jsettlers.graphics.androidui.menu.selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jsettlers.common.images.ImageLink;
import jsettlers.common.movable.EMovableType;
import jsettlers.common.movable.ESoldierLevel;
import jsettlers.common.movable.ESoldierType;
import jsettlers.graphics.androidui.R;

/**
 * The menu that gets displayed when a soldier is selected.
 * @author Michael Zangl
 */
public class SoldierSelectionMenu extends SelectionMenu {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.selection_soldier, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		loadButton(view, R.id.selection_soldier_swordsmen, R.id.selection_soldier_swordsmen_button, ESoldierType.SWORDSMAN, IMAGE_SWORDSMAN);
		loadButton(view, R.id.selection_soldier_bowmen, R.id.selection_soldier_bowmen_button, ESoldierType.BOWMAN, IMAGE_BOWMAN);
		loadButton(view, R.id.selection_soldier_pikemen, R.id.selection_soldier_pikemen_button, ESoldierType.PIKEMAN, IMAGE_PIKEMAN);
		loadButton(view, R.id.selection_soldier_priest, R.id.selection_soldier_priest_button, selection.getMovableCount(EMovableType.BEARER) + "", IMAGE_PRIEST);

		loadMoveButton(view, R.id.selection_soldier_move);
		loadDoWorkButton(view, R.id.selection_soldier_attack);
	}

	private void loadButton(View view, int textId, int buttonId, ESoldierType type, ImageLink image) {
		int[] count = new int[ESoldierLevel.values().length];
		for (EMovableType m : type.getAllOfType()) {
			count[m.getLevel().ordinal()] += selection.getMovableCount(m);
		}

		int max = count.length - 1;
		while (max > 0 && count[max] == 0) {
			// Skip high level zeros.
			max--;
		}
		StringBuilder string = new StringBuilder();
		for (int i = 0; i <= max; i++) {
			if (i != 0) {
				string.append(" ");
			}
			string.append(count[i]);
		}

		loadButton(view, textId, buttonId, string.toString(), image);
	}
}
